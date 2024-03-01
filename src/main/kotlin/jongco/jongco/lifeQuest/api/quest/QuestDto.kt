package jongco.jongco.lifeQuest.api.quest

import jakarta.annotation.Nullable
import org.springframework.web.bind.annotation.RequestParam
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
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
    val startDateTime: Date? = if (startDateTime.isNullOrBlank()) null else {
        Date.from(Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(startDateTime)))
    }
    val endDateTime: Date? = if (endDateTime.isNullOrBlank()) null else {
        Date.from(Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(endDateTime)))
    }
}
