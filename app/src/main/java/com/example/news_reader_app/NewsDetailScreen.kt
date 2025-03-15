import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.news_reader_app.R
import java.text.SimpleDateFormat
import androidx.compose.ui.platform.LocalLayoutDirection

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(newsId: Int, navController: NavController) {
    val layoutDirection = LocalLayoutDirection.current
    val newsTitle = when (newsId) {
        1 -> stringResource(id = R.string.news_1_title)
        2 -> stringResource(id = R.string.news_2_title)
        3 -> stringResource(id = R.string.news_3_title)
        4 -> stringResource(id = R.string.news_4_title)
        5 -> stringResource(id = R.string.news_5_title)
        6-> stringResource(id = R.string.news_6_title)
        7-> stringResource(id = R.string.news_7_title)
        else -> stringResource(id = R.string.news_list_title) // Default
    }

    val newsDescription = when (newsId) {
        1 -> stringResource(id = R.string.news_1_desc)
        2 -> stringResource(id = R.string.news_2_desc)
        3 -> stringResource(id = R.string.news_3_desc)
        4 -> stringResource(id = R.string.news_4_desc)
        5 -> stringResource(id = R.string.news_5_desc)
        6-> stringResource(id = R.string.news_6_desc)
        7 -> stringResource(id = R.string.news_7_desc)
        else -> stringResource(id = R.string.news_list_title) // Default
    }

    val newsImage = when (newsId) {
        1 -> R.drawable.market
        2 -> R.drawable.phone
        3 -> R.drawable.sports
        4 -> R.drawable.storm
        5 -> R.drawable.health
        6 -> R.drawable.mars
        7 -> R.drawable.electric
        else -> R.drawable.market
    }

    var isBookmarked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.news_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            if (layoutDirection == LayoutDirection.Rtl) Icons.Filled.ArrowForward else Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isBookmarked = !isBookmarked }) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = stringResource(id = R.string.bookmark),
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = newsImage),
                contentDescription = newsTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = newsTitle, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = newsDescription, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = getCurrentDate(), fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
    }
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}


