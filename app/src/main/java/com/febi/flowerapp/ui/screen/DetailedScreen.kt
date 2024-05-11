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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.febi.flowerapp.data.Injection
import com.febi.flowerapp.ui.ViewModelFactory
import com.febi.flowerapp.ui.common.UiState
import com.febi.flowerapp.ui.components.CustomTopNavigationBar

@Composable
fun DetailedScreen(
    navController: NavController,
    flowerId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideApi())
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        viewModel.flowerDetailUiState.collectAsState(initial = UiState.Loading).value.let { state ->
            when (state) {
                is UiState.Loading -> {
                    viewModel.getDetailFlower(flowerId)
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
                    DetailContent(
                        navController = navController,
                        viewModel = viewModel,
                        title = dataFlower.title,
                        image = dataFlower.image,
                        description = dataFlower.description,
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    navController: NavController,
    viewModel: DetailViewModel,
    title: String,
    image: Int,
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
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Text(
            text = description,
            fontSize = 14.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 12.dp, end = 12.dp)
        )
    }
}
