package ml.wonwoo.githubissuesdashboard.github

import ml.wonwoo.githubissuesdashboard.GithubProperties
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

@Component
class GithubClient(client: WebClient.Builder, properties: GithubProperties) {

    private val webClient = client.filter(GithubAppTokenInterceptor(properties.token))
        .baseUrl(properties.uri)
        .build()


    fun fetchEvents(orgName: String, repoName: String): Flux<RepositoryEvent> {
        return this.webClient.get().uri("/repos/{owner}/{repo}/issues/events", orgName, repoName)
            .retrieve()
            .bodyToFlux()
    }

    fun fetchEventsList(orgName: String, repoName: String): Flux<RepositoryEvent> {
        return fetchEvents(orgName, repoName)
    }

    private class GithubAppTokenInterceptor(private val token: String?) : ExchangeFilterFunction {

        override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
            val client = this.token?.let {
                val basicAuthValue = it.toByteArray(StandardCharsets.UTF_8)
                ClientRequest.from(request)
                    .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString(basicAuthValue))
                    .build()
            }
            return next.exchange(client ?: request)
        }
    }
}