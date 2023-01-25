package id.fannan.netflixclonedwithcompose.ui.scrollable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LazyColumnSimple() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(text = "Ini Header")
        }
        items(MovieDataSource.getAllMovie()) {
            ItemMovie(itemResId = it)
        }
        item {
            Text(text = "Ini Footer")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviwLazyColumnSimple() {
    LazyColumnSimple()
}