package kth.compose3.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kth.compose3.R

@Composable
fun DrinkCard(drink: Drink, navController: NavController) {
    var isClicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(end = 5.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable { navController.navigate("info/${drink.id}") },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = drink.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(135.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = drink.name,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray,
                )
                Text(
                    text = drink.category.name,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                isClicked = !isClicked
                favoriteDrinks.add(drink)
            }) {
                if (!isClicked) {
                    Image(
                        painter = painterResource(R.drawable.baseline_favorite_gray_border_24),
                        contentDescription = "Heart Button",
                        Modifier.size(20.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.baseline_favorite_red_24),
                        contentDescription = "Heart Button",
                        Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}