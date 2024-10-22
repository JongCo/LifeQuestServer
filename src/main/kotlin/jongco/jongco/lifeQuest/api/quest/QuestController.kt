package jongco.jongco.lifeQuest.api.quest

import jongco.jongco.lifeQuest.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
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
    @Autowired val questService: QuestService,
    @Autowired val jwtTokenProvider: JwtTokenProvider
) {
    @GetMapping("/get")
    fun getQuestsByOwner(
        authentication: Authentication,
    ): QuestsDto {
        val requestUserId: String? = (authentication.details as MutableMap<String, String>)["id"]
        return questService.getQuestsByOwner(UUID.fromString(requestUserId))
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