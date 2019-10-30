package ml.wonwoo.githubissuesdashboard

import ml.wonwoo.githubissuesdashboard.events.GithubProject
import ml.wonwoo.githubissuesdashboard.events.GithubProjectRepository
import ml.wonwoo.githubissuesdashboard.user.User
import ml.wonwoo.githubissuesdashboard.user.UserRepository
import org.springframework.beans.factory.SmartInitializingSingleton
import org.springframework.stereotype.Component

@Component
class MongoInitiailizer(private val githubProjectRepository: GithubProjectRepository,
                        private val userRepository: UserRepository) : SmartInitializingSingleton {

    override fun afterSingletonsInstantiated() {

        val admin = User(password = "{noop}admin", firstName = "wonwoo", lastName = "lee")
        val user = User(password = "{noop}password", firstName = "kevin", lastName = "kim")

        this.userRepository.deleteAll()
            .thenMany(this.userRepository.saveAll(listOf(admin, user)))
            .subscribe { this.userRepository.findAll().subscribe { println(it) } }

        val springBoot = GithubProject(orgName = "spring-projects", repoName = "spring-boot")
//        val springInitializr = GithubProject(orgName = "spring-io", repoName = "initializr")
//        val springSagan = GithubProject(orgName = "spring-io", repoName = "sagan")

        this.githubProjectRepository.deleteAll()
            .thenMany(this.githubProjectRepository.saveAll(listOf(springBoot)))
            .subscribe { this.githubProjectRepository.findAll().subscribe { println(it) } }
    }
}
