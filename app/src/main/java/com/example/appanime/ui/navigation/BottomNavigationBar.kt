import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List


@Composable
fun BottomNavigationBar(navController: NavController) {

    BottomNavigation() {

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Главное меню") },
            selected = false,
            onClick = { navController.navigate("home") }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Anime List") },
            label = { Text("Список аниме") },
            selected = false,
            onClick = { navController.navigate("animeList") }
        )

    }
}