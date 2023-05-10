package kth.compose3.navigation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kth.compose3.R
import kth.compose3.model.Drink
import kth.compose3.model.DrinkCard
import kth.compose3.model.drinks
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kth.compose3.model.DrinkType
import kth.compose3.ui.theme.Orange20

@Composable
fun HomeScreen(navController: NavController, drinkList: List<Drink>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().testTag("item_list"),
        contentPadding = PaddingValues(5.dp)

    ) {
        items(drinkList) { drink ->
            DrinkCard(drink, navController)
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController){
    val drinkId = navController.currentBackStackEntry?.arguments?.getString("id")?.toIntOrNull()
    val plant = drinkId?.let { drinks.getOrNull(it-1) }
    ConstraintLayout{
        val(image, name, category, flavor, ingredients, instructions) = createRefs()
        plant?.let {
            Image(
                painter = painterResource(id = it.imageRes),
                contentDescription = null,
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                    height = Dimension.value(276.dp)
                },
            )
            Text(
                text = it.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })
            Text(
                text = "Category: ${it.category.name}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.constrainAs(category) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })
            Text(
                text = it.flavorProfile,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(flavor) {
                    top.linkTo(name.bottom, margin = 30.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                })
            Text(
                text = it.ingredients,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.constrainAs(ingredients) {
                    top.linkTo(flavor.bottom, margin = 35.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                })
            Text(
                text = it.instructions,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.constrainAs(instructions) {
                    top.linkTo(ingredients.bottom, margin = 30.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                })
        }
    }
}

@Composable
fun FavoritesScreen(navController: NavController, drinkList: List<Drink>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(5.dp)
    ) {
        if (drinkList.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "You have no favorite drinks added",
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                }
            }
        } else {
            items(drinkList) { drink ->
                DrinkCard(drink, navController)
            }
        }
    }
}

@Composable
fun AddDrinkScreen() {
    val name = remember { mutableStateOf("") }
    val flavorProfile = remember { mutableStateOf("") }
    val ingredients = remember { mutableStateOf("") }
    val instructions = remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf(DrinkType.COCKTAIL) }
    var expanded by remember { mutableStateOf(false) }
    val options = DrinkType.values().toList()
    ConstraintLayout {
        val (image, categoryText, category, nameText, nameField, flavorText, flavorField,
            ingredientText, ingredientField, instructionText, instructionField, button) = createRefs()
        val context = LocalContext.current

        Image(
            painter = painterResource(id = R.drawable.drinks),
            contentDescription = null,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
                width = Dimension.matchParent
                height = Dimension.value(276.dp)
            }
        )

        Text(
            text = "Name",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(nameText) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start, margin = 20.dp)
            })
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.constrainAs(nameField) {
                top.linkTo(nameText.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.matchParent
                height = Dimension.value(50.dp)
            }
        )

        Text(
            text = "Category",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(categoryText) {
                top.linkTo(nameField.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
            })
        OutlinedTextField(
            readOnly = true,
            value = selectedOption.name,
            onValueChange = { },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .constrainAs(category) {
                    top.linkTo(categoryText.bottom, margin = 5.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                    width = Dimension.matchParent
                    height = Dimension.value(50.dp)
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = item
                        expanded = false
                    }
                ) {
                    Text(text = item.name)
                }
            }
        }

        Text(
            text = "Flavor Profile",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(flavorText) {
                top.linkTo(category.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
            })
        TextField(
            value = flavorProfile.value,
            onValueChange = { flavorProfile.value = it },
            modifier = Modifier.constrainAs(flavorField) {
                top.linkTo(flavorText.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.matchParent
                height = Dimension.value(50.dp)
            }
        )

        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(ingredientText) {
                top.linkTo(flavorField.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
            })
        TextField(
            value = ingredients.value,
            onValueChange = { ingredients.value = it },
            modifier = Modifier.constrainAs(ingredientField) {
                top.linkTo(ingredientText.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.matchParent
                height = Dimension.value(50.dp)
            }
        )

        Text(
            text = "Instructions",
            style = MaterialTheme.typography.h6,
            fontSize = 15.sp,
            modifier = Modifier.constrainAs(instructionText) {
                top.linkTo(ingredientField.bottom, margin = 10.dp)
                start.linkTo(parent.start, margin = 20.dp)
            })
        TextField(
            value = instructions.value,
            onValueChange = { instructions.value = it },
            modifier = Modifier.constrainAs(instructionField) {
                top.linkTo(instructionText.bottom, margin = 5.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.matchParent
                height = Dimension.value(50.dp)
            }
        )

        Button(
            onClick = {
                Toast.makeText(context, "Drink Added!", Toast.LENGTH_SHORT).show()
                name.value = ""
                flavorProfile.value = ""
                ingredients.value = ""
                instructions.value = ""
            },
            colors = ButtonDefaults.buttonColors(Orange20),
            modifier = Modifier.constrainAs(button) {
                top.linkTo(instructionField.bottom)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.value(200.dp)
            }) {
            Text(text = "Add Drink", color = Color.White)
        }
    }
}