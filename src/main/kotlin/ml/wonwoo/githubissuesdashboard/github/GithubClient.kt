package ml.wonwoo.githubissuesdashboard.github

import com.github.benmanes.caffeine.cache.Caffeine
import ml.wonwoo.githubissuesdashboard.GithubProperties
import org.springframework.cache.interceptor.SimpleKeyGenerator
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.cache.CacheFlux
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit


@Component
class GithubClient(client: WebClient.Builder, properties: GithubProperties) {

    private val webClient = client.filter(GithubAppTokenInterceptor(properties.token))
        .baseUrl(properties.uri)
        .build()

    private var gitHubCache: ConcurrentMap<Any, Any> = Caffeine.newBuilder()
        .maximumSize(1_000)
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .build<Any, Any>()
        .asMap()

    fun fetchEvents(orgName: String, repoName: String): Flux<RepositoryEvent> {
        return this.webClient.get().uri("/repos/{owner}/{repo}/issues/events", orgName, repoName)
            .retrieve()
            .bodyToFlux()
    }

    fun fetchEventsList(orgName: String, repoName: String): Flux<RepositoryEvent> {
        return CacheFlux.lookup<Any, RepositoryEvent>(gitHubCache,
            SimpleKeyGenerator.generateKey(orgName, repoName), RepositoryEvent::class.java)
            .onCacheMissResume {
                fetchEvents(orgName, repoName)
            }
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