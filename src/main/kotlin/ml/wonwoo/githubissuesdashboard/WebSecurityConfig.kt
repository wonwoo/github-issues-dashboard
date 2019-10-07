package ml.wonwoo.githubissuesdashboard

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.actuate.info.InfoEndpoint
import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class WebSecurityConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.authorizeExchange {

            it.matchers(EndpointRequest.to(InfoEndpoint::class.java, HealthEndpoint::class.java)).permitAll()
            it.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            it.matchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
            it.pathMatchers("/events/**").hasRole("USER")
            it.pathMatchers("/admin/**").hasRole("ADMIN")
            it.pathMatchers("/").permitAll()
            it.anyExchange().authenticated()
        }
            .httpBasic()
            .and()
            .formLogin()
            .and()
            .build()
}