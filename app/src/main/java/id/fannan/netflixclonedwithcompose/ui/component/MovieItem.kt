package id.fannan.netflixclonedwithcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.fannan.netflixclonedwithcompose.R
import id.fannan.netflixclonedwithcompose.data.MovieDatasource
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@Composable
fun MovieItem(
    isGrid: Boolean,
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = if (isGrid) movie.posterResourceId else movie.backdropResourceId),
                contentDescription = stringResource(
                    R.string.movie_image
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(220.dp)
            )
            Text(
                text = movie.rating.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(12.dp)
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .background(Color.Black)
                    .padding(9.dp)
                    .align(Alignment.TopEnd)
                    .size(24.dp)
            )
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewMovieItem() {
    NetflixClonedWithComposeTheme {
        MovieItem(isGrid = false, movie = MovieDatasource.getNowPlayingMovie()[0])
    }
}