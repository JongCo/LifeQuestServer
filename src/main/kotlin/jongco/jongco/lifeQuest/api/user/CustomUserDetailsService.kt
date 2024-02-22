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
            .password(passwordEncoder.encode(user.password)) // TODO : 회원가입 시에 암호화한 비밀번호 내용을 encoding하여 비교할 것
            .roles( *user.roles.toTypedArray() )
            .build()
    }

}