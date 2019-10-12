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
import reactor.test.StepVerifier


@ExtendWith(MockitoExtension::class)
internal class RepositoryReactiveUserDetailsServiceTests(@Mock private val userRepository: UserRepository) {

    private val repositoryReactiveUserDetailsService = RepositoryReactiveUserDetailsService(userRepository)

    @Test
    fun `find user name test`() {

        given(userRepository.findByFirstName(any()))
            .willReturn(Mono.just(User(id = "123", password = "www", lastName = "lee", firstName = "wonwoo")))

        val userDetails = repositoryReactiveUserDetailsService.findByUsername("wonwoo")

        StepVerifier.create(userDetails)
            .assertNext {

                assertThat(it.username).isEqualTo("wonwoo")
                assertThat(it.password).isEqualTo("www")
                assertThat(it.authorities).isEqualTo(AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_ACTUATOR", "ROLE_USER"))

            }.verifyComplete()


    }
}