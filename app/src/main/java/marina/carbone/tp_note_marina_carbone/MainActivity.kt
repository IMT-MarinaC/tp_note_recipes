package marina.carbone.tp_note_marina_carbone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import marina.carbone.tp_note_marina_carbone.ui.category_detail.CategoryScreen
import marina.carbone.tp_note_marina_carbone.ui.home.HomeScreen
import marina.carbone.tp_note_marina_carbone.ui.home.HomeViewModel
import marina.carbone.tp_note_marina_carbone.ui.theme.DogbreedsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogbreedsTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable(route = "home") {
                            val homeViewModel: HomeViewModel = viewModel()
                            val homeState by homeViewModel.state.collectAsState()

                            HomeScreen(
                                modifier = Modifier.Companion.padding(paddingValues),
                                //onEvent = homeViewModel::onEvent,
                                state = homeState,
                                onCategoryClick = { cat ->
                                    navController.navigate("detail/${cat.categoryName}")
                                }
                            )
                        }


                        composable(
                            route = "detail/{categoryName}",
                            arguments = listOf(
                                navArgument("categoryName") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val categoryName =
                                backStackEntry.arguments?.getString("categoryName").orEmpty()

                            CategoryScreen(
                                categoryName
                            )
                        }

                    }
                }
            }
        }
    }
}
