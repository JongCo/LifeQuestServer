package jongco.jongco.lifeQuest.security

import jongco.jongco.lifeQuest.security.jwt.JwtAuthenticationFilter
import jongco.jongco.lifeQuest.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig (
    @Autowired
    private final val jwtTokenProvider: JwtTokenProvider
){
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic { disable() }
            csrf { disable() } // TODO : 이게 뭔지 정확히 알아보기
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                authorize(AntPathRequestMatcher("/user/login", HttpMethod.POST.name()), permitAll)
                authorize(AntPathRequestMatcher("/user/register", HttpMethod.POST.name()), permitAll)
                authorize(AntPathRequestMatcher("/error", HttpMethod.POST.name()), anonymous)
                authorize(AntPathRequestMatcher("/user/test", HttpMethod.GET.name()), hasRole("USER"))
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthenticationFilter(jwtTokenProvider))
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}