package io.github.dwivedyaakash.rickandmortyapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.dwivedyaakash.rickandmortyapp.model.Character
import io.github.dwivedyaakash.rickandmortyapp.repository.RickAndMortyApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CharactersUiState(
    val characters: List<Character> = emptyList(),
    val hasNextPage: Boolean = true,
    val currentPage: Int = 1,
    val loading: Boolean = true,
    val loadingMore: Boolean = false
)

class RickAndMortyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    private val repository = RickAndMortyApiRepository()

    private val _characterData = MutableLiveData<Character>()
    val characterData: LiveData<Character> get() = _characterData

    init {
        getCharactersData()
    }

    fun getCharactersData() {
        viewModelScope.launch {
            val currentState = _uiState.value

            try {
                val response = repository.getCharacters(currentState.currentPage)
                if (response.isSuccessful) {
                    _uiState.value =
                        currentState.copy(characters = currentState.characters + response.body()?.results!!)
                } else {
                    Log.e("API Error", "Error fetching characters data: ${response.code()}")
                }
                _uiState.value = _uiState.value.copy(loading = false, loadingMore = false)
            } catch (e: Exception) {
                Log.e("Network Error", e.toString())
                _uiState.value = _uiState.value.copy(loading = false, loadingMore = false)
            }
        }
    }

    fun loadMoreCharacters() {
        val currentState = _uiState.value
        _uiState.value =
            currentState.copy(currentPage = currentState.currentPage + 1, loadingMore = true)
        getCharactersData()
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
