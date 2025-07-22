package io.github.dwivedyaakash.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.dwivedyaakash.rickandmortyapp.model.Character
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Border
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.CardBackground
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Title
import io.github.dwivedyaakash.rickandmortyapp.utils.getCharacterNameColor

@Composable
fun CharacterCard(character: Character, showDetails: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(CardBackground)
            .border(
                color = getCharacterNameColor(character.name, Border),
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .clickable(
                enabled = !showDetails,
                onClick = { onClick() }
            ),
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            model = character.image,
            contentDescription = character.name,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .fillMaxWidth(),
            text = character.name,
            color = getCharacterNameColor(character.name, Title),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = if (showDetails) Start else Center
        )
        if (showDetails) {
            CustomAnnotatedText(character.name, "Status", character.status)
            CustomAnnotatedText(character.name, "Species", character.species)
            CustomAnnotatedText(character.name, "Gender", character.gender)
            CustomAnnotatedText(character.name, "Origin", character.origin.name)
        }
    }
}
