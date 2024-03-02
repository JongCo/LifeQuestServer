package jongco.jongco.lifeQuest.api.quest

import jongco.jongco.lifeQuest.api.user.UserEntity
import jongco.jongco.lifeQuest.api.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.UUID

@Service
class QuestService (
    @Autowired val questRepository: QuestRepository,
    @Autowired val userRepository: UserRepository
){

    fun getQuestsByOwner(owner: UUID): QuestsDto{
        return QuestsDto(questRepository.findAllByOwnerId(owner).map {
            QuestDto(
                it.id.toString(),
                it.title,
                it.owner.id.toString(),
                it.startDateTime?.let { start -> DateTimeFormatter.ISO_DATE_TIME.format(start.toInstant())},
                it.endDateTime?.let { end -> DateTimeFormatter.ISO_DATE_TIME.format(end.toInstant())}
            )
        })
    }

    fun createQuest(title: String, owner:UUID, startDateTime: Date?, endDateTime: Date?): QuestDto{
        val createdQuest = QuestEntity(
            title=title,
            owner=userRepository.findById(owner).get(),
            startDateTime=startDateTime,
            endDateTime=endDateTime
        )
        questRepository.save(createdQuest)
        return QuestDto(createdQuest.id.toString(), createdQuest.owner.id.toString(), createdQuest.title)
    }

    fun getQuestById(id: UUID): QuestDto {
        val selectedQuest = questRepository.findById(id)
        return QuestDto(
            selectedQuest.get().id.toString(),
            selectedQuest.get().owner.id.toString(),
            selectedQuest.get().title
        )
    }
}