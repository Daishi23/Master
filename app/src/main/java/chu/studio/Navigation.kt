package chu.studio

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.KLActivity.route) {
        composable(route = Screen.KLActivity.route) {
            KLActivity()
        }
        composable(route = Screen.AddKLActivity.route) {
            AddKLActivity()
        }
    }
}

sealed class Screen(val route: String) {
    object KLActivity : Screen("kl_activity")
    object AddKLActivity : Screen("add_kl_activity")
}