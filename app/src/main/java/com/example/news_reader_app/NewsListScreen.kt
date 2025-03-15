package com.example.news_reader_app

import NewsArticle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun NewsListScreen(navController: NavController) {
    val newsList = listOf(
        NewsArticle(1, stringResource(id = R.string.news_1_title), stringResource(id = R.string.news_1_desc), R.drawable.market),
        NewsArticle(2, stringResource(id = R.string.news_2_title), stringResource(id = R.string.news_2_desc), R.drawable.phone),
        NewsArticle(3, stringResource(id = R.string.news_3_title), stringResource(id = R.string.news_3_desc), R.drawable.sports),
        NewsArticle(4, stringResource(id = R.string.news_4_title), stringResource(id = R.string.news_4_desc), R.drawable.storm),
        NewsArticle(5, stringResource(id = R.string.news_5_title), stringResource(id = R.string.news_5_desc), R.drawable.health),
        NewsArticle(6, stringResource(id = R.string.news_6_title), stringResource(id = R.string.news_6_desc), R.drawable.mars),
        NewsArticle(7, stringResource(id = R.string.news_7_title), stringResource(id = R.string.news_7_desc), R.drawable.electric),

        )

    Column {
        Text(
            text = stringResource(id = R.string.news_list_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn {
            items(newsList) { news ->
                NewsItem(news) {
                    navController.navigate("news_detail/${news.id}")
                }
            }
        }
    }
}

@Composable
fun NewsItem(news: NewsArticle, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = news.imageResId),
                contentDescription = news.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = news.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}