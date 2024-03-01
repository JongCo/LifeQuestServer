package jongco.jongco.lifeQuest.api.quest

import jakarta.persistence.*
import jongco.jongco.lifeQuest.api.stage.StageEntity
import jongco.jongco.lifeQuest.api.user.UserEntity
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(name = "quest")
@Table(name = "quest")
class QuestEntity (
    @Id
    @UuidGenerator
    val id:UUID = UUID.randomUUID(),

    @ManyToOne(targetEntity = UserEntity::class)
    val owner: UserEntity,

    @Column(length = 200)
    var title:String,

    @OneToMany(mappedBy = "quest", cascade = [CascadeType.REMOVE])
    val stages: List<StageEntity> = ArrayList(),

    @Column
    val createDateTime: Date = Date(System.currentTimeMillis()),

    @Column(nullable = true)
    var startDateTime: Date?,

    @Column(nullable = true)
    var endDateTime: Date?,
)

interface QuestRepository: JpaRepository<QuestEntity, UUID> {
    fun findAllByOwnerId(owner: UUID): List<QuestEntity>
}