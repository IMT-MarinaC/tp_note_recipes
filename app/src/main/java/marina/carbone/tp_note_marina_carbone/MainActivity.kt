package marina.carbone.tp_note_marina_carbone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import marina.carbone.tp_note_marina_carbone.ui.route.category.CategoryScreen
import marina.carbone.tp_note_marina_carbone.ui.route.home.HomeScreen
import marina.carbone.tp_note_marina_carbone.ui.route.home.HomeViewModel
import marina.carbone.tp_note_marina_carbone.ui.route.meal.MealScreen
import marina.carbone.tp_note_marina_carbone.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                val navController = rememberNavController()

                Scaffold(
                    containerColor = Color.Transparent
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier
                            .fillMaxSize()
                            .windowInsetsPadding(
                                WindowInsets.safeDrawing.only(
                                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                                )
                            ),
                    ) {
                        composable(route = "home") {
                            val homeViewModel: HomeViewModel = viewModel()
                            val homeState by homeViewModel.state.collectAsState()

                            HomeScreen(
                                //modifier = Modifier.Companion.padding(paddingValues),
                                state = homeState,
                                onCategoryClick = { cat ->
                                    navController.navigate("category/${cat.categoryName}")
                                }
                            )
                        }

                        composable(
                            route = "category/{categoryName}",
                            arguments = listOf(
                                navArgument("categoryName") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val categoryName =
                                backStackEntry.arguments?.getString("categoryName").orEmpty()

                            CategoryScreen(
                                modifier = Modifier,
                                categoryName,
                                viewModel = viewModel(),
                                onMealClick = { mealPreview ->
                                    navController.navigate("meal/${mealPreview.id}")
                                }
                            )
                        }

                        composable(
                            route = "meal/{mealId}",
                            arguments = listOf(
                                navArgument("mealId") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val mealId =
                                backStackEntry.arguments?.getString("mealId").orEmpty()

                            MealScreen(
                                mealId
                            )
                        }

                    }
                }
            }
        }
    }
}
