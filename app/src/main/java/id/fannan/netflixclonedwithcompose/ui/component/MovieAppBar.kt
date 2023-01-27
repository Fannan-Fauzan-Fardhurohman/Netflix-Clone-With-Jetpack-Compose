package id.fannan.netflixclonedwithcompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.fannan.netflixclonedwithcompose.R
import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    imageResourceId: Int = R.drawable.ic_netflix
) {
    CenterAlignedTopAppBar(modifier = modifier,
        title = {
            Image(
                painter = painterResource(
                    id = imageResourceId
                ),
                contentDescription = stringResource(R.string.app_bar_image),
                modifier = Modifier.height(35.dp)
            )
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun MovieAppbarImage() {
    NetflixClonedWithComposeTheme {
        MovieAppBar()
    }
}