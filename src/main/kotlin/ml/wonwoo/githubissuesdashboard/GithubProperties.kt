package ml.wonwoo.githubissuesdashboard

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Pattern

@ConfigurationProperties("github")
@ConstructorBinding
@Validated
data class GithubProperties(

    @Pattern(regexp = "\\w+:\\w+")
    val token: String?,

    val uri: String

)