package io.github.dwivedyaakash.rickandmortyapp.apiService

import io.github.dwivedyaakash.rickandmortyapp.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}
