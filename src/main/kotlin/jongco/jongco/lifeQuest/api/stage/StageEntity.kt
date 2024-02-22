package jongco.jongco.lifeQuest.api.stage

import jakarta.persistence.*
import jongco.jongco.lifeQuest.api.quest.QuestEntity
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity(name = "stage")
@Table(name = "stage")
class StageEntity (
    @Id
    @UuidGenerator
    val id: UUID = UUID.randomUUID(),

    @Column(length = 200)
    var title: String,

    @ManyToOne
    val quest: QuestEntity
)