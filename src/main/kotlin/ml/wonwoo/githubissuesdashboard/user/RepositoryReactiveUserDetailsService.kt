package ml.wonwoo.githubissuesdashboard.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class RepositoryReactiveUserDetailsService(private val userRepository: UserRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {

        return this.userRepository.findByFirstName(username)
            .map { CustomUserDetails(it) }

    }

    class CustomUserDetails(private val user: User) : UserDetails {

        override fun getPassword(): String = user.password

        override fun getAuthorities(): Collection<GrantedAuthority> =
            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_ACTUATOR", "ROLE_USER")

        override fun getUsername(): String = user.firstName

        override fun isAccountNonExpired(): Boolean = true

        override fun isAccountNonLocked(): Boolean = true

        override fun isCredentialsNonExpired(): Boolean = true

        override fun isEnabled(): Boolean = true
    }

}