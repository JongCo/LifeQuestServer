package jongco.jongco.lifeQuest.api.user

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID
import java.util.stream.Collectors

@Entity
@Table(name = "user")
class UserEntity (
    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID(),

    @Column(length = 30, nullable = false, unique = true)
    val userName: String,

    @Column(nullable = false)
    val passWord: String,

    //idk
    @ElementCollection(fetch = FetchType.EAGER)
    val roles: List<String> = ArrayList()
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map{ SimpleGrantedAuthority(it) }.toMutableList()
    }

    override fun getPassword(): String {
        return passWord
    }

    override fun getUsername(): String {
        return userName
    }

    // TODO : 이 밑으로 의미 파악 잘 해서 수정하기
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
