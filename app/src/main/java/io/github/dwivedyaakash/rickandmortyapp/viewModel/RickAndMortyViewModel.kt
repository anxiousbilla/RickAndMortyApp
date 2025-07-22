package io.github.dwivedyaakash.rickandmortyapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.dwivedyaakash.rickandmortyapp.model.Character
import io.github.dwivedyaakash.rickandmortyapp.model.CharacterResponse
import io.github.dwivedyaakash.rickandmortyapp.repository.RickAndMortyApiRepository
import kotlinx.coroutines.launch

class RickAndMortyViewModel : ViewModel() {
    private val repository = RickAndMortyApiRepository()

    private val _charactersData = MutableLiveData<CharacterResponse>()
    val charactersData: LiveData<CharacterResponse> get() = _charactersData

    private val _characterData = MutableLiveData<Character>()
    val characterData: LiveData<Character> get() = _characterData

    fun getCharactersData() {
        viewModelScope.launch {
            try {
                val response = repository.getCharacters()
                if (response.isSuccessful) {
                    _charactersData.value = response.body()
                } else {
                    Log.e("API Error", "Error fetching characters data: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Network Error", e.toString())
            }
        }
    }

    fun getCharacterByIdData(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getCharacterById(id)
                if (response.isSuccessful) {
                    _characterData.value = response.body()
                } else {
                    Log.e("API Error", "Error fetching character data: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Network Error", e.toString())
            }
        }
    }
}
