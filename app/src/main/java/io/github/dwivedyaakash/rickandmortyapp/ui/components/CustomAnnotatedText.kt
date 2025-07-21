package io.github.dwivedyaakash.rickandmortyapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Description
import io.github.dwivedyaakash.rickandmortyapp.ui.theme.Title
import io.github.dwivedyaakash.rickandmortyapp.utils.getCharacterNameColor

@Composable
fun CustomAnnotatedText(name: String, label: String, text: String) {
    Text(
        text = buildAnnotatedString {
            append("${label}: ")
            pushStyle(
                SpanStyle(
                    color = getCharacterNameColor(name, Title),
                    fontWeight = FontWeight.Bold
                )
            )
            append(text)
        }, color = Description
    )
}
