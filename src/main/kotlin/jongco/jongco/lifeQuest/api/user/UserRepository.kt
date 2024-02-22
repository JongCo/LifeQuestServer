package jongco.jongco.lifeQuest.api.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByUserName(userName: String): UserEntity
}