package jongco.jongco.lifeQuest.api.stage

import jakarta.persistence.*
import jongco.jongco.lifeQuest.api.quest.QuestEntity
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@Entity(name = "stage")
@Table(name = "stage")
class StageEntity (
    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID(),

    @Column(length = 200)
    var title: String,

    @ManyToOne(targetEntity = QuestEntity::class)
    val quest: QuestEntity
)

interface StageRepository: JpaRepository<StageEntity, UUID>{
    fun findAllByQuest(quest: UUID): List<StageEntity>
}