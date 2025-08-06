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
import paul.vickers.built4life.repository.WorkoutRepository
import paul.vickers.built4life.ui.navigation.Routes

class AddScoreViewModel(
    private val scoreRepository: ScoreRepository,
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val workoutId = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutId
    val workoutTitle = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutTitle
    val workoutWeight = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutWeight ?: 0
    val workoutReps = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutReps ?: 0
    val workoutOneRepMax = savedStateHandle.toRoute<Routes.AddScoreScreen>().workoutOneRepMax ?: 0


    private val _repsInput = MutableStateFlow("")
    val repsInput: StateFlow<String> = _repsInput.asStateFlow()

    private val _weightInput = MutableStateFlow("")
    val weightInput: StateFlow<String> = _weightInput.asStateFlow()


//    private val _scores = MutableStateFlow<List<Score>>(emptyList())
//    val scores: StateFlow<List<Score>> = _scores.asStateFlow()

//    private val _oneRepMax = MutableStateFlow(0L)
//    val oneRepMax: MutableStateFlow<Long> = _oneRepMax

//    init {
//        viewModelScope.launch { getMaxScore() }
////        getScoresByWorkoutId(workoutId)
//    }

    fun onRepsTxtChange(newTxt: String) {
        _repsInput.value = newTxt
//        _scoreTitleInput.value = newTxt
    }

    fun onWeightTxtChange(newTxt: String) {
        _weightInput.value = newTxt
    }

//    fun onSubmitClick() {
//        viewModelScope.launch {
//            upsertScore() // maybe have a trigger here in my sql instead, like on update score will update max score???
//            getMaxScore()
//            updateMaxScore()
//        }
//    }

    fun upsertScore() {
        if (_repsInput.value.isNotEmpty()) {
            viewModelScope.launch {
                scoreRepository.upsert(
                    Score(
                        reps = _repsInput.value.toLong(),
                        weight = _weightInput.value.toLong(),
                        workoutId = workoutId
                    )
                )
            }
        }
//        _scoreState.value.copy(
//            reps = ""
//        )
    }

//    fun upsertWorkout() {
//        viewModelScope.launch {
//            workoutTitle?.let {
//                workoutRepository.upsert(
//                    Workout(
//                        id = workoutId,
//                        title = it,
//                        maxScore = _maxScore.value,
//                        weight = workoutWeight,
//                        eliteLevel = workoutEliteLevel
//                    )
//                )
//            }
//        }
//    }


//    suspend fun getMaxScore() {
//        _oneRepMax.value = workoutId?.let { scoreRepository.getMaxScore(it) } ?: 0
//    }
//
//    suspend fun updateMaxScore() {
//        workoutId?.let { workoutRepository.updateMaxScore(it, _oneRepMax.value) }
//    }

//    fun getScoresByWorkoutId(workoutId: Long?) {
//        viewModelScope.launch {
//            workoutId?.let { scoreRepository.getScoresByWorkoutId(it) }?.collect { scores ->
//                _scores.value = scores
//            }
//        }
//
//    }
}