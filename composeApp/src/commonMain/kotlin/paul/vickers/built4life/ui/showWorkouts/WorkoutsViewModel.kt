package paul.vickers.built4life.ui.showWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import paul.vickers.built4life.model.WorkoutWithMaxScore
import paul.vickers.built4life.repository.WorkoutRepository

class WorkoutsViewModel(
    private val workoutRepository: WorkoutRepository,
//    private val scoreRepository: ScoreRepository,
//    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val workoutId = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutId
//    private val maxScoreState = savedStateHandle.toRoute< Routes.WorkoutsScreen>().maxScore

//    private val _maxScore = MutableStateFlow<Long?>(0L)
//    val maxScore: MutableStateFlow<Long?> = _maxScore
//
//    private val _lastScore = MutableStateFlow(0)
//    val lastScore: StateFlow<Int> = _lastScore

//    init {
//        getMaxScore(workoutId)
//    }

    val workouts: StateFlow<List<WorkoutWithMaxScore>> = workoutRepository.workoutsWithMaxScores
        .map { it }
        .stateIn(
            scope = viewModelScope, // Use viewModelScope for coroutine lifecycle management
            started = SharingStarted.Companion.WhileSubscribed(5000), // Keep the flow active while subscribed (with a 5s grace period)
            initialValue = emptyList() // Provide an initial empty list
        )

// fun getMaxScore(workoutId: Long?) {
//        viewModelScope.launch {
//            _maxScore.value = workoutId?.let { scoreRepository.getMaxScore(it) }?: 0
//
//        }
//    }

//    fun getLastScore() {
//        viewModelScope.launch {
//            _lastScore.value = scoreRepository.getLastScore()
//        }
//    }


}