package jongco.jongco.lifeQuest.api.user

import jongco.jongco.lifeQuest.api.user.dto.UserRegisterResponseDto
import jongco.jongco.lifeQuest.security.jwt.JwtTokenProvider
import jongco.jongco.lifeQuest.security.jwt.TokenInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    @Autowired val userRepository: UserRepository,
    @Autowired val authenticationManagerBuilder: AuthenticationManagerBuilder,
    @Autowired val jwtTokenProvider: JwtTokenProvider
){
    @Transactional
    fun login(username: String, password: String): TokenInfo {
        // TODO : Delete print after tested
        println("Login Function Executed")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        println(authenticationToken.name)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        println(authentication.isAuthenticated)

        return jwtTokenProvider.generateToken(authentication)
    }

    fun register(username: String, password: String): UserRegisterResponseDto {
            val createdUser = UserEntity(
                userName = username,
                passWord = password
            )
            userRepository.save(createdUser)
            return UserRegisterResponseDto("$username has been successfully created")
    }
}