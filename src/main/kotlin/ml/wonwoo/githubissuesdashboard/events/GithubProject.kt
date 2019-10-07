package ml.wonwoo.githubissuesdashboard.events

data class GithubProject(

    val id: String? = null,

    val orgName: String,

    val repoName: String
)