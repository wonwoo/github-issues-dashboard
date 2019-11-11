package ml.wonwoo.githubissuesdashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GithubIssuesDashboardApplication

fun main(args: Array<String>) {
    runApplication<GithubIssuesDashboardApplication>(*args)
}
