package jongco.jongco.lifeQuest.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired val userRepository: UserRepository,
    @Autowired val passwordEncoder: BCryptPasswordEncoder
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return createUserDetails(userRepository.findByUserName(username))
    }

    fun createUserDetails(user: UserEntity): UserDetails {
        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles( *user.roles.toTypedArray() )
            .build()
    }

}