package jongco.jongco.lifeQuest.api.category

import jakarta.persistence.*
import jongco.jongco.lifeQuest.api.user.UserEntity
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(name = "category")
@Table(name = "category")
class CategoryEntity (
    @Id
    @UuidGenerator
    val id:UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = UserEntity::class)
    val owner: UserEntity,

    @Column(length = 100)
    var title: String,

    @Column
    val createDateTime: Instant = Instant.now(),

    @Column
    var modifiedDateTime: Instant,

    @Column
    var sortOrder: Int,
)

interface CategoryRepository: JpaRepository<CategoryEntity, UUID> {
    fun findAllByOwnerId(owner: UUID): List<CategoryEntity>
    fun findTopByOwnerIdOrderBySortOrderDesc(owner: UUID): CategoryEntity
}