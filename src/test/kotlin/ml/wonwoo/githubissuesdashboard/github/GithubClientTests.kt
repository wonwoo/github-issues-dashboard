package ml.wonwoo.githubissuesdashboard.github

import ml.wonwoo.githubissuesdashboard.GithubProperties
import ml.wonwoo.githubissuesdashboard.readString
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import reactor.kotlin.test.test

internal class GithubClientTests {

    private val mockWebServer = MockWebServer()

    private val githubClient: GithubClient = GithubClient(WebClient.builder().baseUrl(mockWebServer.url("/").toString())
        , GithubProperties(null, mockWebServer.url("/").toString()))

    @Test
    fun `github fetch events test`() {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .setBody(ClassPathResource("events.json", this.javaClass).readString())
        )

        val event = githubClient.fetchEventsList("spring-projects", "spring-boot")

        event.test().assertNext {

            assertThat(it.actor.avatarUrl).isEqualTo("https://avatars0.githubusercontent.com/u/1761408?v=4")
            assertThat(it.actor.htmlUrl).isEqualTo("https://github.com/mbhave")
            assertThat(it.actor.login).isEqualTo("mbhave")
            assertThat(it.issue.title).isEqualTo("@AutoConfigureMockMvc imports auto-configurations manually")
            assertThat(it.issue.number).isEqualTo(13822)

        }.assertNext {

            assertThat(it.actor.avatarUrl).isEqualTo("https://avatars3.githubusercontent.com/u/914682?v=4")
            assertThat(it.actor.htmlUrl).isEqualTo("https://github.com/wilkinsona")
            assertThat(it.actor.login).isEqualTo("wilkinsona")
            assertThat(it.issue.title).isEqualTo("@AutoConfigureMockMvc imports auto-configurations manually")
            assertThat(it.issue.number).isEqualTo(13822)

        }.verifyComplete()
    }
}