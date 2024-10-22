package jongco.jongco.lifeQuest.api.category

import jongco.jongco.lifeQuest.api.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class CategoryService (
    @Autowired val categoryRepository: CategoryRepository,
    @Autowired val userRepository: UserRepository
){

    fun getCategoriesByOwner(owner: UUID): CategoriesDto{
        return CategoriesDto(categoryRepository.findAllByOwnerId(owner).map {
            CategoryDto(
                it.id.toString(),
                it.owner.id.toString(),
                it.title,
                it.createDateTime.toString(),
                it.modifiedDateTime.toString(),
                it.sortOrder
            )
        })
    }

    fun createCategory(title: String, owner: UUID): CategoryDto{
        val topOrder = categoryRepository.findTopByOwnerIdOrderBySortOrderDesc(owner)?.sortOrder


        val createdCategory = CategoryEntity(
            title = title,
            owner = userRepository.findById(owner).get(),
            modifiedDateTime = Instant.now(),
            sortOrder = if(topOrder!=null) {topOrder + 1} else {1}
        )

        categoryRepository.save(createdCategory)
        return CategoryDto(
            createdCategory.id.toString(),
            createdCategory.owner.id.toString(),
            createdCategory.title,
            createdCategory.createDateTime.toString(),
            createdCategory.modifiedDateTime.toString(),
            createdCategory.sortOrder
        )
    }
}