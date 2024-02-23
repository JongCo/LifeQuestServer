package jongco.jongco.lifeQuest.api.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByUserName(userName: String): UserEntity
}