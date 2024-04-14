package jongco.jongco.lifeQuest.api.stage

class StageDto (
    val id: String,
    val quest: String,
    val title: String,
)

class CreateStageRequestDto (
    val quest: String,
    val title: String,
)