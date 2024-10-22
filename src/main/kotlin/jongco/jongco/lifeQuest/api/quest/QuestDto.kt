package jongco.jongco.lifeQuest.api.quest

import java.time.Instant
import java.util.UUID

class QuestDto(
    val id: String,
    val owner: String,
    val title: String,
    val startDateTime: String? = null,
    val endDateTime: String? = null
)

class QuestsDto(
    val quests: List<QuestDto>
)

class GetQuestsByUuidRequestDto (
    uuid: String,
) {
    val uuid: UUID = UUID.fromString(uuid)
}

class CreateQuestRequestDto (
    owner: String,
    val title: String,
    startDateTime: String?,
    endDateTime: String?
) {
    val owner: UUID = UUID.fromString(owner)
    val startDateTime: Instant? = if (startDateTime.isNullOrBlank()) null else {
        Instant.parse(startDateTime)
    }
    val endDateTime: Instant? = if (endDateTime.isNullOrBlank()) null else {
        Instant.parse(endDateTime)
    }
}
