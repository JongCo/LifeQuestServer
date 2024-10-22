package jongco.jongco.lifeQuest.api.category

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/category")
class CategoryController (
    @Autowired val categoryService: CategoryService,
){
    @GetMapping("/get")
    fun getCategoryByOwner(
        authentication: Authentication,
    ): CategoriesDto{
        val requestUserId: String? = (authentication.details as MutableMap<String, String>)["id"]
        return categoryService.getCategoriesByOwner(UUID.fromString(requestUserId))
    }

    @PostMapping("/create")
    fun createCategory(
        @RequestBody createCategoryDto: CreateCategoryDto,
        authentication: Authentication,
    ): CategoryDto{
        return categoryService.createCategory(
            createCategoryDto.title,
            UUID.fromString((authentication.details as MutableMap<String, String>)["id"])
        )
    }
}