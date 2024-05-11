package com.febi.flowerapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febi.flowerapp.data.Injection
import com.febi.flowerapp.ui.ViewModelFactory
import com.febi.flowerapp.ui.common.UiState
import com.febi.flowerapp.ui.components.FlowerGridCard
import com.febi.flowerapp.ui.components.SectionText

@Composable
fun SecondScreen(
    viewModel: SecondViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideApi())
    ),
    onClickDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeContent(viewModel = viewModel, onClickDetail = onClickDetail, modifier = modifier)
}

@Composable
fun HomeContent(
    viewModel: SecondViewModel,
    onClickDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = modifier.padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 4.dp,
                        horizontal = 4.dp
                    )
            ) {
                Text(
                    text = "Flower Definition",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
        SectionText(
            title = "Flower",
            content = {
                viewModel.flowerState.collectAsState(initial = UiState.Loading).value.let { state ->
                    when(state){
                        is UiState.Loading -> {
                            Text(
                                text = "Sedang mengambil data...",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                            viewModel.getFlowers()
                        }
                        is UiState.Error -> {
                            Text(
                                text = "Terjadi kesalahan saat memuat data. Silakan coba lagi nanti.",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Red,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                        is UiState.Success -> {
//                            Text(
//                                text = "Success",
//                                style = MaterialTheme.typography.titleMedium,
//                                color = Color.Red,
//                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
//                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                                    .height(600.dp)
                                    .fillMaxWidth()
                            ) {
                                items(state.data){data ->
                                    FlowerGridCard(flowerData = data, onClickDetail = onClickDetail)
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

