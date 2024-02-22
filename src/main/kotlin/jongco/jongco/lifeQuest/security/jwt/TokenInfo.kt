package jongco.jongco.lifeQuest.security.jwt

class TokenInfo (
    val grantType: String,
    val accessToken: String,
    val refreshToken: String
)