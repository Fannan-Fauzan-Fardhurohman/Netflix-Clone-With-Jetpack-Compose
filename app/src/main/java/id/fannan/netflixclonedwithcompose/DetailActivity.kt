package id.fannan.netflixclonedwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.ui.screen.MovieDetailScreen
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@ExperimentalMaterial3Api
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getParcelable<Movie>(EXTRA_MOVIE)?.let { movie ->
            setContent {
                NetflixClonedWithComposeTheme {
                    MovieDetailScreen(movie = movie) {
                        finish()
                    }

                }
            }

        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}
