package ml.wonwoo.githubissuesdashboard.user

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, String> {

    fun findByFirstName(firstName: String): Mono<User>

}