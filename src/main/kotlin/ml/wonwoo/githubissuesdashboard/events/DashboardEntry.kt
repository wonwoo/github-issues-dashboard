package ml.wonwoo.githubissuesdashboard.events

import ml.wonwoo.githubissuesdashboard.github.RepositoryEvent

data class DashboardEntry(

    val project: GithubProject,

    val events: List<RepositoryEvent>

)