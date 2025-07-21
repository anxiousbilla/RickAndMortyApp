package io.github.dwivedyaakash.rickandmortyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.dwivedyaakash.rickandmortyapp.ui.components.CharacterCard
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Background
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Title
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
            .background(Background)
            .padding(vertical = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, bottom = 22.dp),
            text = "Rick And Morty Characters",
            color = Title,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            charactersData?.results?.forEach {
                item {
                    CharacterCard(it)
                }
            }
        }
    }

}
