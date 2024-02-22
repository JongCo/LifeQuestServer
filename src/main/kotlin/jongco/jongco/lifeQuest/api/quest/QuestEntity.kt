package jongco.jongco.lifeQuest.api.quest

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jongco.jongco.lifeQuest.api.stage.StageEntity
import org.hibernate.annotations.UuidGenerator
import java.util.UUID

@Entity
@Table(name = "quest")
class QuestEntity (
    @Id
    @UuidGenerator
    val id:UUID = UUID.randomUUID(),

    @Column(length = 200)
    var title:String,

    @OneToMany(mappedBy = "quest", cascade = [CascadeType.REMOVE])
    val stages: List<StageEntity>
)