package com.example.firsttep.presentation.news_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsttep.data.remote.Resource
import com.example.firsttep.domain.use_case.get_headline.GetHeadLineUseCase
import com.example.firsttep.domain.use_case.get_recommended.GetRecommendedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getHeadLine: GetHeadLineUseCase,
    private val getRecommendedUseCase: GetRecommendedUseCase,
) : ViewModel() {

    private val _headlineState: MutableState<MainState> = mutableStateOf(MainState.Loading)
    val headlineState: State<MainState> = _headlineState

    private val _recommendedState: MutableState<MainState> = mutableStateOf(MainState.Loading)
    val recommendNewsState: State<MainState> = _recommendedState

    init {
        getHeadLineNews("us")
        getRecommendedNews()
    }

    private fun getHeadLineNews(country: String) {
        getHeadLine(country).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _headlineState.value = MainState.ReceiveNews(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _headlineState.value = MainState.OnError(
                        error = ErrorState(
                            error = result.message,
                            displayMessage = "An unexpected error occurred"
                        )
                    )
                }
                is Resource.Loading -> {
                    _headlineState.value = MainState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecommendedNews(query: String = "today") {
        getRecommendedUseCase(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _recommendedState.value = MainState.ReceiveNews(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _recommendedState.value = MainState.OnError(
                        error = ErrorState(
                            error = result.message,
                            displayMessage = "An unexpected error occurred"
                        )
                    )
                }
                is Resource.Loading -> {
                    _recommendedState.value = MainState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }


}