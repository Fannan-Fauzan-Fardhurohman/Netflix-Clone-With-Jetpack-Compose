package id.fannan.netflixclonedwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.fannan.netflixclonedwithcompose.data.MovieDatasource
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.ui.component.MovieAppBar
import id.fannan.netflixclonedwithcompose.ui.component.MovieItem
import id.fannan.netflixclonedwithcompose.ui.screen.MovieGridScreen
import id.fannan.netflixclonedwithcompose.ui.screen.MovieListScreen
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixClonedWithComposeTheme {
                NetflixCloneApps()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NetflixCloneApps() {
    val movies: List<Movie> by rememberSaveable {
        mutableStateOf(MovieDatasource.getNowPlayingMovie())
    }

    var isGrid by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieAppBar(
                onViewChange = { isGrid = it }
            )
        }) { contentPadding ->
        if (isGrid) MovieGridScreen(contentPadding, movies)
        else MovieListScreen(contentPadding, movies)
    }
}