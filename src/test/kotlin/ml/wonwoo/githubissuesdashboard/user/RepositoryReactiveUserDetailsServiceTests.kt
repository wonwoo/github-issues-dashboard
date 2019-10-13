package ml.wonwoo.githubissuesdashboard.user

import ml.wonwoo.githubissuesdashboard.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.authority.AuthorityUtils
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.kotlin.test.test
import reactor.test.StepVerifier


@ExtendWith(MockitoExtension::class)
internal class RepositoryReactiveUserDetailsServiceTests(@Mock private val userRepository: UserRepository) {

    private val repositoryReactiveUserDetailsService = RepositoryReactiveUserDetailsService(userRepository)

    @Test
    fun `find user name test`() {

        given(userRepository.findByFirstName(any()))
            .willReturn(User(id = "123", password = "www", lastName = "lee", firstName = "wonwoo").toMono())

        val userDetails = repositoryReactiveUserDetailsService.findByUsername("wonwoo")

        userDetails.test()
            .assertNext {

                assertThat(it.username).isEqualTo("wonwoo")
                assertThat(it.password).isEqualTo("www")
                assertThat(it.authorities).isEqualTo(AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_ACTUATOR", "ROLE_USER"))

            }.verifyComplete()


    }
}