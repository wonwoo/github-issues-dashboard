package ml.wonwoo.githubissuesdashboard.events

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface GithubProjectRepository : ReactiveMongoRepository<GithubProject, Long> {

    fun findByRepoName(repoName: String): Mono<GithubProject>

}