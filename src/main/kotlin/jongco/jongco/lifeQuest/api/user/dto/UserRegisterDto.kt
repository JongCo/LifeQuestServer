package jongco.jongco.lifeQuest.api.user.dto

class UserRegisterRequestDto (
    val userName: String,
    val passWord: String
)

class UserRegisterResponseDto (
    val result: String
)