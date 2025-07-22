package io.github.dwivedyaakash.rickandmortyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.dwivedyaakash.rickandmortyapp.ui.components.CharacterCard
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Background
import io.github.dwivedyaakash.rickandmortyapp.viewModel.RickAndMortyViewModel

@Composable
fun CharacterDetailScreen(id: Int?) {
    val viewModel = remember { RickAndMortyViewModel() }
    val characterData by viewModel.characterData.observeAsState()

    // Fetch character data
    id?.let { viewModel.getCharacterByIdData(it) }

    characterData?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(vertical = 10.dp)
        ) {
            CharacterCard(
                it,
                showDetails = true,
                onClick = {}
            )
        }
    }
}
