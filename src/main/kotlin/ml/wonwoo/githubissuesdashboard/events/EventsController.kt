package ml.wonwoo.githubissuesdashboard.events

import ml.wonwoo.githubissuesdashboard.github.GithubClient
import ml.wonwoo.githubissuesdashboard.github.RepositoryEvent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.Flux

@Controller
class EventsController(private val githubClient: GithubClient, private val repository: GithubProjectRepository) {

    @GetMapping("/events/{projectName}")
    @ResponseBody
    fun fetchEvents(@PathVariable projectName: String): Flux<RepositoryEvent> {

        return this.repository.findByRepoName(projectName)
            .flatMapMany { this.githubClient.fetchEvents(it.orgName, it.repoName) }
    }

    @GetMapping("/")
    fun dashboard(): Rendering {

        val entries = this.repository.findAll()
            .flatMap {
                githubClient.fetchEventsList(it.orgName, it.repoName).collectList()
                    .map { repositoryEvent -> DashboardEntry(it, repositoryEvent) }
            }

        return Rendering.view("dashboard")
            .modelAttribute("entries", entries)
            .build()
    }

    @GetMapping("/admin")
    fun admin(): Rendering {

        return Rendering.view("admin")
            .modelAttribute("projects", repository.findAll())
            .build()

    }

    @PostMapping("/admin")
    fun createGithubProject(@ModelAttribute githubProject: GithubProject): Rendering {

        return Rendering.redirectTo("/")
            .modelAttribute("entries", this.repository.save(githubProject))
            .build()

    }
}