package ml.wonwoo.githubissuesdashboard.events

import ml.wonwoo.githubissuesdashboard.WebSecurityConfig
import ml.wonwoo.githubissuesdashboard.any
import ml.wonwoo.githubissuesdashboard.github.Actor
import ml.wonwoo.githubissuesdashboard.github.GithubClient
import ml.wonwoo.githubissuesdashboard.github.Issue
import ml.wonwoo.githubissuesdashboard.github.RepositoryEvent
import ml.wonwoo.githubissuesdashboard.github.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec
import org.springframework.test.web.reactive.server.expectBodyList
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.OffsetDateTime

@WebFluxTest(EventsController::class)
@Import(WebSecurityConfig::class)
@WithMockUser
internal class EventsControllerTests(@Autowired private val webClient: WebTestClient) {

    @MockBean
    private lateinit var githubClient: GithubClient

    @MockBean
    private lateinit var repository: GithubProjectRepository

    @Test
    fun `fetch events test`() {

        given(repository.findByRepoName(any()))
            .willReturn(Mono.just(GithubProject(orgName = "wonwoo", repoName = "github-issues-dashboard")))

        val repositoryEvent = RepositoryEvent(Type.DEMILESTONED, OffsetDateTime.now(),
            Actor("wonwoo", "http://localhost:1111/avatar", "http://localhost:1111/html"),
            Issue("http://localhost:1111/html", 1110, "sboot"))

        given(githubClient.fetchEvents(any(), any()))
            .willReturn(Flux.just(repositoryEvent))

        this.webClient.get()
            .uri("/events/wonwoo").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList<RepositoryEvent>()
            .hasSize(1)
            .value<ListBodySpec<RepositoryEvent>> {
                assertThat(it[0].type).isEqualTo(Type.DEMILESTONED)
                assertThat(it[0].actor.login).isEqualTo("wonwoo")
                assertThat(it[0].actor.avatarUrl).isEqualTo("http://localhost:1111/avatar")
                assertThat(it[0].actor.htmlUrl).isEqualTo("http://localhost:1111/html")

                assertThat(it[0].issue.htmlUrl).isEqualTo("http://localhost:1111/html")
                assertThat(it[0].issue.number).isEqualTo(1110)
                assertThat(it[0].issue.title).isEqualTo("sboot")
            }
    }
}