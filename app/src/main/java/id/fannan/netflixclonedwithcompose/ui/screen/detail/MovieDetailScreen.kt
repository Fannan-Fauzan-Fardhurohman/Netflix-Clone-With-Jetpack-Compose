package id.fannan.netflixclonedwithcompose.ui.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.fannan.netflixclonedwithcompose.data.MovieDatasource
import id.fannan.netflixclonedwithcompose.domain.model.Movie
import id.fannan.netflixclonedwithcompose.ui.component.MovieAppBar

@ExperimentalMaterial3Api
@Composable
fun MovieDetailScreen(
    movieId: String,
    navHostController: NavHostController,
    viewModel: MovieDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = MovieDetailViewModel.Factory)
) {

    val movie by viewModel.movie.collectAsState()
    LaunchedEffect(movieId) {
        viewModel.getMovieDetail(movieId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.Black)
        ) {
            val (backdropRef, topBarRef, ratingRef, buttonRef, overviewRef) = createRefs()
            movie?.let { movie ->
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropResourceId).crossfade(true).build(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(backdropRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.ratio("2:3")
                            height = Dimension.fillToConstraints
                        }
                        .drawWithCache {
                            createVerticalGradient(0, 5f)
                        },
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .drawWithCache {
                        createVerticalGradient(1, 2f)
                    })

                Row(modifier = Modifier.constrainAs(ratingRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "${movie.rating}", style = TextStyle(
                            color = Color.White
                        )
                    )
                }
                Button(modifier = Modifier.constrainAs(buttonRef) {
                    top.linkTo(ratingRef.bottom, 16.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }, onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "",
                        tint = Color.Black
                    )
                    Text(text = "Play", style = TextStyle(color = Color.Black))
                }
                ContentOverview(
                    modifier = Modifier.constrainAs(overviewRef) {
                        top.linkTo(buttonRef.bottom, 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, movie = movie
                )

            }
            MovieAppBar(modifier = Modifier.constrainAs(topBarRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, isTransparent = true, onBack = { navHostController.popBackStack() })

        }
    }
}

@Composable
private fun ContentOverview(modifier: Modifier = Modifier, movie: Movie) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (overviewRef, imageRef, titleRef, descRef) = createRefs()
        Text(
            modifier = Modifier.constrainAs(overviewRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = "Overview",
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
        )
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .constrainAs(imageRef) {
                    top.linkTo(overviewRef.bottom, 16.dp)
                    start.linkTo(parent.start)
                    width = Dimension.ratio("2:3")
                    height = Dimension.value(150.dp)
                },
            model = ImageRequest.Builder(LocalContext.current).data(movie.backdropResourceId)
                .crossfade(true).build(),
            contentDescription = ""

        )
        Text(
            modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(imageRef.top)
                    start.linkTo(imageRef.end, 16.dp)
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end)

                }, text = movie.title, style = TextStyle(
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp
            )
        )

        Text(
            modifier = Modifier.constrainAs(descRef) {
                    top.linkTo(titleRef.bottom, 8.dp)
                    start.linkTo(imageRef.end, 16.dp)
                    width = Dimension.fillToConstraints
                    end.linkTo(parent.end)

                }, text = movie.description, style = TextStyle(
                color = Color.White
            )
        )
    }
}


/**
 *
 *  @param direction 0 -> down to up, 1 -> up to down
 *  @param slicing 1f -> 1:1, nf = 1/n
 */


private fun CacheDrawScope.createVerticalGradient(
    direction: Int,
    slicing: Float,
): DrawResult {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = size.height / if (direction == 0) slicing else 1f,
        endY = size.height / if (direction == 1) slicing else 1f
    )
    return onDrawWithContent {
        drawContent()
        drawRect(gradientBrush, blendMode = BlendMode.Multiply)
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewContentOverview() {
    ContentOverview(movie = MovieDatasource.getNowPlayingMovie()[1])
}
