package io.github.dwivedyaakash.rickandmortyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.dwivedyaakash.rickandmortyapp.Screens
import io.github.dwivedyaakash.rickandmortyapp.ui.components.CharacterCard
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Background
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Title
import io.github.dwivedyaakash.rickandmortyapp.viewModel.RickAndMortyViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = remember { RickAndMortyViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val charactersData = uiState.characters
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                if (lastVisibleItem >= totalItems - 2 && uiState.hasNextPage && !uiState.loading && !uiState.loadingMore) {
                    viewModel.loadMoreCharacters()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(vertical = 10.dp)
    ) {
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
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
            }
            // Shimmer
            if (charactersData.isEmpty()) {
                for (i in 1..6) {
                    item {
                        CharacterCard(
                            character = null,
                            showDetails = false,
                            onClick = {}
                        )
                    }
                }
            }
            charactersData.forEach {
                item {
                    CharacterCard(
                        character = it,
                        showDetails = false,
                        onClick = { navController.navigate(Screens.CharacterDetailScreen.name + "/${it.id}") }
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                if (charactersData.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentAlignment = Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(30.dp),
                            color = Color(0xFFEEEEEE)
                        )
                    }
                }
            }
        }
    }

}
