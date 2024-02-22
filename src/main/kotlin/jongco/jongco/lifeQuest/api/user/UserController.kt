package jongco.jongco.lifeQuest.api.user

import jongco.jongco.lifeQuest.api.user.dto.UserLoginRequestDto
import jongco.jongco.lifeQuest.api.user.dto.UserRegisterRequestDto
import jongco.jongco.lifeQuest.api.user.dto.UserRegisterResponseDto
import jongco.jongco.lifeQuest.security.jwt.TokenInfo
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
        val userName = userLoginRequestDto.userName
        val passWord = userLoginRequestDto.passWord

        return userService.login(userName, passWord)
    }

    @PostMapping("/register")
    fun register(@RequestBody userRegisterRequestDto: UserRegisterRequestDto): UserRegisterResponseDto {
        try {
            val userName = userRegisterRequestDto.userName
            val passWord = userRegisterRequestDto.passWord
            return userService.register(userName, passWord)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "aa")
        }
    }
}