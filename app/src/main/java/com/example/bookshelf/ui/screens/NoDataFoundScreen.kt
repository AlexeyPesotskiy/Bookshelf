package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R

/**
 * Экран с сообщением о том, что данные по запросу отсутствуют
 */
@Composable
fun NoDataFoundScreen(modifier: Modifier = Modifier) =
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.no_data_img),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.image_size)),
        )
        Text(
            text = stringResource(R.string.no_data_found)
        )
    }