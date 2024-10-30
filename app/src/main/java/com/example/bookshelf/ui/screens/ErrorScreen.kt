package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookshelf.R

/**
 * Экран сообщения об ошибке при загрузке списка
 *
 * @param onRetry лямбда-функция для повторной попытки загрузки списка книг
 */
@Composable
fun ErrorScreen(onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.medium_padding),
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(R.drawable.error_img),
            contentDescription = stringResource(R.string.error),
            modifier = Modifier.size(dimensionResource(R.dimen.image_size))
        )
        Text(
            text = stringResource(R.string.error_loading),
            textAlign = TextAlign.Center,
            modifier = Modifier.width(dimensionResource(R.dimen.error_message_width))
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.width(dimensionResource(R.dimen.image_size))
        ) {
            Text(stringResource(R.string.retry))
        }
    }
}