package jongco.jongco.lifeQuest.api.stage

import jongco.jongco.lifeQuest.api.quest.QuestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StageService (
    @Autowired val stageRepository: StageRepository,
    @Autowired val questRepository: QuestRepository
) {
    fun createStage(
        quest: UUID,
        title: String,
    ): StageDto {
        val createdStage = StageEntity(
            title = title,
            quest = questRepository.findById(quest).get(),
        )
        stageRepository.save(createdStage)

        return StageDto(
            createdStage.id.toString(),
            createdStage.quest.id.toString(),
            createdStage.title
        )
    }

}