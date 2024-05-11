package com.febi.flowerapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.febi.flowerapp.data.Injection
import com.febi.flowerapp.ui.ViewModelFactory
import com.febi.flowerapp.ui.common.UiState
import com.febi.flowerapp.ui.components.CustomTopNavigationBar

@Composable
fun SecondDetailedScreen(
    navController: NavController,
    flowerMonthId: Long,
    viewModel: SecondDetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideApi())
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        viewModel.flowerMonthUiState.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getDetailFlowerMonth(flowerMonthId)
                }
                is UiState.Error -> {
                    Text(
                        text = "Terjadi kesalahan saat memuat data Detailed Screen. Silakan coba lagi nanti.",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Red,
                        modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
                is UiState.Success -> {
                    val dataFlower = state.data
                    SecondDetailContent(
                        navController = navController,
                        viewModel = viewModel,
                        title = dataFlower.title,
                        source = dataFlower.source,
                        image = dataFlower.image,
                        funfact = dataFlower.funfact,
                        description = dataFlower.desc,
                    )
                }
            }
        }
    }
}

@Composable
fun SecondDetailContent(
    navController: NavController,
    viewModel: SecondDetailViewModel,
    title: String,
    image: Int,
    source: String,
    funfact: String,
    description: String,
    modifier: Modifier = Modifier
) {
    CustomTopNavigationBar(title = title, navController = navController)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "source: $source",
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            color = Color.LightGray,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 12.dp, end = 12.dp)
        )
        Text(
            text = "Funfact: $funfact",
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        )
        Text(
            text = description,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, start = 12.dp, end = 12.dp)
        )
    }
}
