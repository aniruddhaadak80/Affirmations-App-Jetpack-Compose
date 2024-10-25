package com.project.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.affirmations.data.Datasource
import com.project.affirmations.model.Affirmation
import com.project.affirmations.ui.screen.AffirmationCard
import com.project.affirmations.ui.screen.AffirmationDetailScreen
import com.project.affirmations.ui.screen.AffirmationList
import com.project.affirmations.ui.screen.TopAppBar
import com.project.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "list") {
                        composable("list") {
                            AffirmationsApp(navController)
                        }
                        composable("details/{quote}/{author}/{imageId}") { backStackEntry ->
                            val quote = backStackEntry.arguments?.getString("quote")?.toIntOrNull() ?: 0
                            val author = backStackEntry.arguments?.getString("author")?.toIntOrNull() ?: 0
                            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull() ?: 0
                            AffirmationDetailScreen(
                                navController = navController,
                                quoteId = quote,
                                authorId = author,
                                imageId = imageId
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(navController = navController, showBackButton = false)
        },
        content = { paddingValues ->
            AffirmationList(
                affirmationList = Datasource().loadAffirmations(),
                onClick = { affirmation ->
                    navController.navigate("details/${affirmation.stringResourceId}/${affirmation.authorResourceId}/${affirmation.imageResourceId}")
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1, R.string.author1))
}

@Preview(showBackground = true)
@Composable
private fun AffirmationAppPreview() {
    val navController = rememberNavController()
    AffirmationsApp(navController = navController)
}
