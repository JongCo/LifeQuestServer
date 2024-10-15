package jongco.jongco.lifeQuest.api.category

import java.util.UUID

class CategoryDto(
    val id: String,
    val owner: String,
    val title: String,
)

class CategoriesDto(
    val categories: List<CategoryDto>
)

class GetCategoriesByUuidDto(
    uuid: String,
) {
    val uuid: UUID = UUID.fromString(uuid)
}

class CreateCategoryDto(
    val title: String
)