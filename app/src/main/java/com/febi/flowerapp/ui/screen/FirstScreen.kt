package com.febi.flowerapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.febi.flowerapp.data.Injection
import com.febi.flowerapp.ui.ViewModelFactory
import com.febi.flowerapp.ui.common.UiState
import com.febi.flowerapp.ui.components.FlowerLazyColumnCard
import com.febi.flowerapp.ui.components.FlowerLazyRowCard
import com.febi.flowerapp.ui.components.SecondSectionText
import com.febi.flowerapp.ui.components.SectionText

@Composable
fun FirstScreen(
    viewModel: FirstViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideApi())
    ),
    onClickDetail : (Long) -> Unit,
    onClickDetailSecond: (Long) -> Unit,
    modifier : Modifier = Modifier
) {
    //Tambahkan function yg dibuat di bawah ini
    FirstContent(
        viewModel = viewModel,
        onClickDetail = onClickDetail ,
        onClickDetailSecond = onClickDetailSecond,
        modifier = modifier)
}

@Composable
fun FirstContent(
    viewModel : FirstViewModel,
    onClickDetail: (Long) -> Unit,
    onClickDetailSecond: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
   Column(
       modifier = modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())

   ){
       SectionText(
           title = "Spring Flowers",
           content = {
               viewModel.flowerUiState.collectAsState(initial = UiState.Loading).value.let { state ->
                   when(state){
                       is UiState.Loading -> {
                           Text(
                               text = "Sedang mengambil data...",
                               style = MaterialTheme.typography.titleMedium,
                               color = Color.Black,
                               modifier = Modifier
                                   .padding(horizontal = 12.dp, vertical = 4.dp)
                           )
                           viewModel.getFlower()
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
                           LazyRow(
                               horizontalArrangement = Arrangement.spacedBy(8.dp),
                               contentPadding = PaddingValues(8.dp),
                           ) {
                               items(state.data){data ->
                                   FlowerLazyRowCard(flowerData = data, onClickDetail = onClickDetail)
                               }
                           }

                       }
                   }
               }
           }
       )
       SecondSectionText(
           title = "Flower Birth Month",
           desc = "Embrace your birth month with the enchanting beauty of your unique birth flower",
           //tambah teks sedikit menjelaskan kenapa ada flower birth month ini
           content = {
               viewModel.flowerBirthUiState.collectAsState(initial = UiState.Loading).value.let { state ->
                   when(state){
                       is UiState.Loading -> {
                           Text(
                               text = "Sedang mengambil data...",
                               style = MaterialTheme.typography.titleMedium,
                               color = Color.Black,
                               modifier = Modifier
                                   .padding(horizontal = 12.dp, vertical = 4.dp)
                           )
                           viewModel.getFlowerBirth()
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
                           LazyColumn(
                               modifier = Modifier
                                   .padding(horizontal = 12.dp, vertical = 4.dp)
                                   .height(600.dp)
                                   .fillMaxWidth()
                                   .align(Alignment.CenterHorizontally)
                           ) {
                               items(state.data){data ->
                                   FlowerLazyColumnCard(flowerData = data, onClickDetail = onClickDetailSecond)
                               }
                           }

                       }
                   }
               }
           }
       )

   }
}