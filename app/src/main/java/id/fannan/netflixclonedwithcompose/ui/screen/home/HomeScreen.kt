package id.fannan.netflixclonedwithcompose.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import id.fannan.netflixclonedwithcompose.ui.MovieViewModel
import id.fannan.netflixclonedwithcompose.ui.component.MovieAppBar
import id.fannan.netflixclonedwithcompose.ui.component.MovieSearchField
import id.fannan.netflixclonedwithcompose.ui.screen.list.MovieGridScreen
import id.fannan.netflixclonedwithcompose.ui.screen.list.MovieListScreen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
) {
    val movies by viewModel.movies.collectAsState(arrayListOf())


    var isGrid by remember { mutableStateOf(false) }

    var keyword by remember {
        mutableStateOf("")
    }

    LaunchedEffect("") {
        viewModel.getMovies()
    }
    LaunchedEffect(keyword) {
        viewModel.searchMovie(keyword)
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                MovieAppBar(
                    onViewChange = { isGrid = it }
                )
                MovieSearchField(
                    keyword, onTextChange = {
                        keyword = it
                    },
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }) { contentPadding ->
        if (isGrid) MovieGridScreen(contentPadding, movies, navHostController)
        else MovieListScreen(contentPadding, movies, navHostController)
    }
}