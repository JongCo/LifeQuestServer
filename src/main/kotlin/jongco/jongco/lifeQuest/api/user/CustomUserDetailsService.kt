package jongco.jongco.lifeQuest.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired val userRepository: UserRepository,
    @Autowired val passwordEncoder: BCryptPasswordEncoder
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserEntity {
        return userRepository.findByUsername(username)
    }

//    fun createUserDetails(user: UserEntity): UserDetails {
//        return User.builder()
//            .username(user.username)
//            .password(user.password)
//            .roles( *user.roles.toTypedArray() )
//            .build()
//    }

}