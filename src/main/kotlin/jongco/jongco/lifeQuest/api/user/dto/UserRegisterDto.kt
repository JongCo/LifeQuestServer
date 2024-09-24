package jongco.jongco.lifeQuest.api.user.dto

class UserRegisterRequestDto (
    val username: String,
    val password: String
)

class UserRegisterResponseDto (
    val result: String
)