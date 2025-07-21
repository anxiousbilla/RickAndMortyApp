package io.github.dwivedyaakash.rickandmortyapp.utils

import androidx.compose.ui.graphics.Color
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.AccentBlue
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.AccentYellow

fun getCharacterNameColor(name: String, defaultColor: Color): Color {
    return when (name) {
        "Rick Sanchez" -> AccentBlue
        "Morty Smith" -> AccentYellow
        else -> defaultColor
    }
}
