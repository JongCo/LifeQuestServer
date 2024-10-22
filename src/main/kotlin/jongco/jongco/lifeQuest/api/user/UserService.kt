package jongco.jongco.lifeQuest.api.user

import jongco.jongco.lifeQuest.api.user.dto.UserDto
import jongco.jongco.lifeQuest.api.user.dto.UserRegisterResponseDto
import jongco.jongco.lifeQuest.security.jwt.JwtTokenProvider
import jongco.jongco.lifeQuest.security.jwt.TokenInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService (
    @Autowired val userRepository: UserRepository,
    @Autowired val authenticationManagerBuilder: AuthenticationManagerBuilder,
    @Autowired val jwtTokenProvider: JwtTokenProvider
){

    fun login(username: String, password: String): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        println(authenticationToken.name)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        println(authentication.isAuthenticated)

        return jwtTokenProvider.generateToken(authentication)
    }

    fun register(username: String, password: String): UserRegisterResponseDto {
            val createdUser = UserEntity(
                username = username,
                password = BCryptPasswordEncoder().encode(password),
                roles = List<String>(1){"USER"}
            )
            userRepository.save(createdUser)
            return UserRegisterResponseDto("$username has been successfully created")
    }

    fun getUserInfo(uuid: UUID): UserDto {
        val user = userRepository.findById(uuid)
        return UserDto(
            username = user.get().username,
            userId = user.get().id.toString()
        )
    }
}