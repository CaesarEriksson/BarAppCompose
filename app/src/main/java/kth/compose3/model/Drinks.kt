package kth.compose3.model

import kth.compose3.R

const val flavor = "Bloody Mary is a classic cocktail known for its bold and savory flavor profile. It typically has a rich and tangy tomato base" +
        "infused with a combination of spices and seasonings. The primary flavors in a Bloody Mary include the savory notes of Worcestershire sauce" +
        "the heat of hot sauce or Tabasco, the tanginess of lemon or lime juice, and the aromatic blend of celery salt and black pepper."

const val ingredients = "Servings: 1 " + "\n" +
        "Large handful of ice\n100ml Vodka\n500ml Tomato juice\n1 tbsp lemon juice\nFew shakes Worcestershire sauce\nFew shakes Tabasco\nPinch black pepper"

const val instructions = "Place the ice in a large jug. Pour the vodka, tomato juice, lemon juice and 3 shakes of Worcestershire sauce and Tabasco." +
        "Add a pinch of celery salt and pepper. Stir until the outside of the jug feels cold"

val drinks = listOf(
    Drink(
        1,
        R.drawable.bloody_mary,
        "Bloody Mary",
        DrinkType.COCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        2,
        R.drawable.boulevardier,
        "Boulevardier",
        DrinkType.MOCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        3,
        R.drawable.daiquiri,
        "Daiquiri",
        DrinkType.SPIRIT,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        4,
        R.drawable.michelada,
        "Michelada",
        DrinkType.BEER,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        5,
        R.drawable.espresso_martini,
        "Espresso Martini",
        DrinkType.MOCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        6,
        R.drawable.manhattan,
        "Manhattan",
        DrinkType.COCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        7,
        R.drawable.margarita,
        "Margarita",
        DrinkType.SPIRIT,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        8,
        R.drawable.mojito,
        "Mojito",
        DrinkType.COCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        9,
        R.drawable.old_fashioned,
        "Old Fashioned",
        DrinkType.MOCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
    Drink(
        10,
        R.drawable.dry_martini,
        "Dry Martini",
        DrinkType.COCKTAIL,
        flavor,
        ingredients,
        instructions
    ),
)

val favoriteDrinks = mutableListOf<Drink>()

val duplicatedDrinks = List(5) { drinks }.flatten()


