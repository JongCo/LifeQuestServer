package jongco.jongco.lifeQuest.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jongco.jongco.lifeQuest.api.user.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.security.Key
import java.util.Arrays
import java.util.Date
import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import kotlin.reflect.typeOf

@Component
class JwtTokenProvider(@Value("\${jwt.secret}") secretKey: String) {
    private final val keyBytes: ByteArray
    private final val key: Key

    init {
        this.keyBytes = Decoders.BASE64.decode(secretKey)
        this.key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(authentication: Authentication): TokenInfo{
        val authorities: String = authentication.authorities.stream()
            .map { it.authority }
            .collect(Collectors.joining(","))

        val loggedInUserEntity = authentication.principal as UserEntity


        val now = Date()
        val accessTokenExpiresIn = Date(now.time + TimeUnit.DAYS.toMillis(10))
        val refreshTokenExpiresIn = Date(now.time + TimeUnit.DAYS.toMillis(10))

        val accessToken = Jwts.builder()
            .setSubject(authentication.name)
            .claim("id", loggedInUserEntity.id)
            .claim("auth", authorities)
            .setExpiration(accessTokenExpiresIn)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken = Jwts.builder()
            .setExpiration(refreshTokenExpiresIn)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo(
            grantType = "Bearer",
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims: Claims = parseClaims(accessToken)

        if (claims["auth"] == null) {
            throw RuntimeException("권한 정보가 없는 토큰입니다.")
        }

        val authorities: Collection<GrantedAuthority> = claims["auth"].toString()
            .split((","))
            .map { SimpleGrantedAuthority(it) }


        val principal: UserDetails = User(claims.subject, "", authorities)
        val authenticationToken = UsernamePasswordAuthenticationToken(principal, "", authorities)
        val details: MutableMap<String, String> = mutableMapOf()
        details["id"] = claims["id"].toString()
        authenticationToken.details = details
        return authenticationToken
    }

    fun validateToken(token: String): Boolean {
        try {
            val parsedClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            println(e)
        }
        return false
    }

    fun parseClaims(accessToken: String): Claims {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            return e.claims;
        }
    }
}