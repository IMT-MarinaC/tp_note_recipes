package marina.carbone.tp_note_marina_carbone.data.network

import marina.carbone.tp_note_marina_carbone.data.network.dot.response.CategoryListResponse
import marina.carbone.tp_note_marina_carbone.data.network.dot.response.MealListByCategoryResponse
import marina.carbone.tp_note_marina_carbone.data.network.dot.response.MealListResponse
import marina.carbone.tp_note_marina_carbone.data.network.dot.response.MealResponse
import marina.carbone.tp_note_marina_carbone.domain.model.Category
import marina.carbone.tp_note_marina_carbone.domain.model.Ingredient
import marina.carbone.tp_note_marina_carbone.domain.model.Meal
import marina.carbone.tp_note_marina_carbone.domain.model.MealPreview


/**** CATEGORIE ****/
fun CategoryListResponse.toDomain(): List<Category> {
    return this.categories.map {
        Category(
            idCategory = it.idCategory,
            categoryName = it.categoryName,
            categoryThumb = it.categoryThumb,
            categoryDescription = it.categoryDescription
        )
    }
}


/**** MEAL ****/
fun MealListResponse.toDomain(): List<Meal> {
    return this.meals.map {
        it.toDomain()
    }
}

fun MealListByCategoryResponse.toDomain(): List<MealPreview> {
    return this.meals.orEmpty().map {
        MealPreview(
            id = it.id,
            name = it.name,
            thumbnail = it.thumbnail,
        )
    }
}

fun MealResponse.toDomain(): Meal {
    val ingredients = listOf(
        ingredient1 to measure1,
        ingredient2 to measure2,
        ingredient3 to measure3,
        ingredient4 to measure4,
        ingredient5 to measure5,
        ingredient6 to measure6,
        ingredient7 to measure7,
        ingredient8 to measure8,
        ingredient9 to measure9,
        ingredient10 to measure10,
        ingredient11 to measure11,
        ingredient12 to measure12,
        ingredient13 to measure13,
        ingredient14 to measure14,
        ingredient15 to measure15,
        ingredient16 to measure16,
        ingredient17 to measure17,
        ingredient18 to measure18,
        ingredient19 to measure19,
        ingredient20 to measure20
    ).mapNotNull { (ingredient, measure) ->
        if (!ingredient.isNullOrBlank()) {
            Ingredient(
                name = ingredient.trim(),
                measure = measure?.trim().orEmpty()
            )
        } else null
    }

    return Meal(
        id = idMeal,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        thumbnail = thumbnail,
        tags = tags?.split(",")?.map { it.trim() } ?: emptyList(),
        youtubeUrl = youtubeUrl,
        ingredients = ingredients
    )
}


