package jongco.jongco.lifeQuest.api.quest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/quest")
class QuestController (
    @Autowired val questService: QuestService
) {
    @GetMapping("/get")
    fun getQuestsByOwner(@RequestBody getQuestsByUuidRequestDto: GetQuestsByUuidRequestDto): QuestsDto {
        return questService.getQuestsByOwner(getQuestsByUuidRequestDto.uuid)
    }

    @PostMapping("/create")
    fun createQuest(@RequestBody createQuestRequestDto: CreateQuestRequestDto): QuestDto {
        return questService.createQuest(
            createQuestRequestDto.title,
            createQuestRequestDto.owner,
            createQuestRequestDto.startDateTime,
            createQuestRequestDto.endDateTime
        )
    }
    @GetMapping("/get/{id}")
    fun getQuest(@PathVariable("id") id: String): QuestDto{
        return questService.getQuestById(UUID.fromString(id))
    }
}