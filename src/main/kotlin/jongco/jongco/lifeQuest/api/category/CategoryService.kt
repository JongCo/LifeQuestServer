package jongco.jongco.lifeQuest.api.category

import jongco.jongco.lifeQuest.api.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

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
            )
        })
    }

    fun createCategory(title: String, owner: UUID): CategoryDto{
        val createdCategory = CategoryEntity(
            title=title,
            owner=userRepository.findById(owner).get(),
        )

        categoryRepository.save(createdCategory)
        return CategoryDto(
            createdCategory.id.toString(),
            createdCategory.owner.id.toString(),
            createdCategory.title
        )
    }
}