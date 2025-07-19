package paul.vickers.built4life.ui.addScore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.model.Score
import paul.vickers.built4life.repository.ScoreRepository
import paul.vickers.built4life.ui.navigation.Routes

class AddScoreViewModel(
    private val scoreRepository: ScoreRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val workoutId = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutId

    private val _scoreTitleInput = MutableStateFlow("")
    val scoreTitleInput: StateFlow<String> = _scoreTitleInput.asStateFlow()

    private val _scores = MutableStateFlow<List<Score>>(emptyList())
    val scores: StateFlow<List<Score>> = _scores.asStateFlow()

    init {
        getScoresByWorkoutId(workoutId ?: 0)
    }

    fun onTxtChange(newTxt: String) {
        _scoreTitleInput.value = newTxt
    }

    fun upsertScore() {
        if (_scoreTitleInput.value.isNotEmpty()) {
            viewModelScope.launch {
                scoreRepository.upsert(
                    Score(
                        reps = _scoreTitleInput.value.toLong(),
                        workoutId = workoutId
                    )
                )
            }
        }
        _scoreTitleInput.value = ""
    }

    fun getScoresByWorkoutId(workoutId: Long) {
        viewModelScope.launch {
            scoreRepository.getScoresByWorkoutId(workoutId).collect { scores ->
                _scores.value = scores
            }
        }

    }
}