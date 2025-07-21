package io.github.dwivedyaakash.rickandmortyapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dwivedyaakash.rickandmortyapp.viewModel.RickAndMortyViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = remember { RickAndMortyViewModel() }
    val charactersData by viewModel.charactersData.observeAsState()

    // Fetch characters
    viewModel.getCharactersData()

    Column(
        modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        charactersData?.results[0]?.name?.let { Text(it) }
    }

}
