package com.febi.flowerapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextInfoProfile(
    first: String,
    second: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
    ) {
        Text(
            text = first + ": ",
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
        Text(
            text = second,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
    }
}

@Composable
fun SectionText(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(start = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 12.dp))
        content()
    }
}

@Composable
fun SecondSectionText(
    title: String,
    desc: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp)
            .padding(start = 4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic),
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        content()
    }
}

