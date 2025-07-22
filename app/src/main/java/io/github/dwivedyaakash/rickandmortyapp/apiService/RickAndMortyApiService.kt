package io.github.dwivedyaakash.rickandmortyapp.apiService

import io.github.dwivedyaakash.rickandmortyapp.model.Character
import io.github.dwivedyaakash.rickandmortyapp.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>
}
