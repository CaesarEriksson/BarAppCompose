package kth.compose3.model

data class Drink(
    val id: Int,
    val imageRes : Int,
    val name: String,
    val category: DrinkType,
    val flavorProfile: String,
    val ingredients: String,
    val instructions: String
)

enum class DrinkType {
    COCKTAIL,
    MOCKTAIL,
    BEER,
    WINE,
    SPIRIT
}

