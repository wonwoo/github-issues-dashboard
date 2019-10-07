package ml.wonwoo.githubissuesdashboard.user

import org.springframework.data.mongodb.core.index.Indexed
import javax.validation.constraints.NotEmpty

data class User(

    var id: String? = null,

    @NotEmpty(message = "This field is required")
    val password: String,

    @NotEmpty(message = "This field is required")
    @Indexed
    val firstName: String,

    @NotEmpty(message = "This field is required")
    val lastName: String

)
