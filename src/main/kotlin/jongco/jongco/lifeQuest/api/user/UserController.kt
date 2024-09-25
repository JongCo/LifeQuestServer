package jongco.jongco.lifeQuest.api.user

import jongco.jongco.lifeQuest.api.user.dto.UserLoginRequestDto
import jongco.jongco.lifeQuest.api.user.dto.UserRegisterRequestDto
import jongco.jongco.lifeQuest.api.user.dto.UserRegisterResponseDto
import jongco.jongco.lifeQuest.security.jwt.TokenInfo
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/user")
class UserController (
    @Autowired
    val userService: UserService
){

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequestDto: UserLoginRequestDto): TokenInfo {
        try {
            val username = userLoginRequestDto.username
            val password = userLoginRequestDto.password

            return userService.login(username, password)
        } catch (e: Exception) {
            print(e.stackTrace)
            for (trace: StackTraceElement in e.stackTrace){
                println(trace)
            }
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "shit")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody userRegisterRequestDto: UserRegisterRequestDto): UserRegisterResponseDto {
        try {
            val username = userRegisterRequestDto.username
            val password = userRegisterRequestDto.password
            return userService.register(username, password)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "aa")
        }
    }
}