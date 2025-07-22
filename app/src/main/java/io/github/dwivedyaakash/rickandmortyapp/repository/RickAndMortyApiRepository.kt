package io.github.dwivedyaakash.rickandmortyapp.repository

import io.github.dwivedyaakash.rickandmortyapp.model.Character
import io.github.dwivedyaakash.rickandmortyapp.model.CharacterResponse
import io.github.dwivedyaakash.rickandmortyapp.retrofit.RetrofitInstance
import retrofit2.Response

class RickAndMortyApiRepository {
    private val rickAndMortyApiService = RetrofitInstance.rickAndMortyApiService

    suspend fun getCharacters(): Response<CharacterResponse> {
        return rickAndMortyApiService.getCharacters()
    }

    suspend fun getCharacterById(id: Int): Response<Character> {
        return rickAndMortyApiService.getCharacterById(id)
    }
}
