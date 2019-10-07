package ml.wonwoo.githubissuesdashboard

import org.springframework.boot.context.properties.ImmutableConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Pattern

@ImmutableConfigurationProperties("github")
@Validated
data class GithubProperties(

    @Pattern(regexp = "\\w+:\\w+")
    val token: String?,

    val uri: String

)