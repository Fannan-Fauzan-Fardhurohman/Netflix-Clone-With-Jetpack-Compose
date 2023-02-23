package id.fannan.netflixclonedwithcompose.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.fannan.netflixclonedwithcompose.data.MovieDatasource
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.ui.Routers
import id.fannan.netflixclonedwithcompose.ui.component.MovieAppBar
import id.fannan.netflixclonedwithcompose.ui.component.MovieItem
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@ExperimentalMaterial3Api
@Composable
fun MovieListScreen(
    paddingValues: PaddingValues,
    movies: List<Movie>,
    navHostController: NavHostController
) {

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                isGrid = false,
                movie = movie,
                modifier = Modifier.padding(horizontal = 16.dp),
                onItemClick = { movie ->
                    navHostController.navigate("${Routers.DETAIL}/${movie.id}")
                }

            )
        }
    }
}