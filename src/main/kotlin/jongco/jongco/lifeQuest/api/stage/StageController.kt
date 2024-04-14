package jongco.jongco.lifeQuest.api.stage

import jongco.jongco.lifeQuest.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/stage")
class StageController (
    @Autowired val stageService: StageService,
    @Autowired val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/create")
    fun createStage(@RequestBody createRequest: CreateStageRequestDto): StageDto {
        return stageService.createStage(
            UUID.fromString(createRequest.quest),
            createRequest.title
        )
    }
}