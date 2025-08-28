package paul.vickers.built4life.features.days.ui.dayScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import paul.vickers.built4life.features.days.model.DayWorkout
import paul.vickers.built4life.features.days.repository.DayWorkoutRepository
import paul.vickers.built4life.features.scores.model.Score
import paul.vickers.built4life.features.scores.repository.ScoreRepository
import paul.vickers.built4life.features.workouts.repository.WorkoutRepository
import paul.vickers.built4life.navigation.Routes

class DayScreenViewModel(
    private val dayWorkoutRepository: DayWorkoutRepository,
    private val workoutRepository: WorkoutRepository,
    private val scoreRepository: ScoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val dayTitle = savedStateHandle.toRoute<Routes.DayScreen>().dayTitle
    val dayId = savedStateHandle.toRoute<Routes.DayScreen>().dayId

    private val _repsInput = MutableStateFlow("")
    val repsInput: StateFlow<String> = _repsInput.asStateFlow()

    private val _weightInput = MutableStateFlow("")
    val weightInput: StateFlow<String> = _weightInput.asStateFlow()




    val dayWorkouts: StateFlow<List<DayWorkout>> = dayWorkoutRepository.getDayWorkoutsByDayId(dayId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val workouts = workoutRepository.workouts

    fun onRepsTxtChange(newTxt: String) {
        _repsInput.value = newTxt
    }

    fun onWeightTxtChange(newTxt: String) {
        _weightInput.value = newTxt
    }




    fun upsertDayWorkout(dayWorkout: DayWorkout) {
        viewModelScope.launch {
            dayWorkoutRepository.upsert(
                DayWorkout(
                    dayId = dayId,
                    workoutId = dayWorkout.workoutId
                )

            )
        }
    }

    fun deleteDayWorkout(dayWorkout: DayWorkout) {
        viewModelScope.launch {
            dayWorkoutRepository.delete(dayWorkout)
        }
    }

    fun addSet(workoutId: Long, dayWorkoutId: Long?) {
        viewModelScope.launch {
            scoreRepository.upsert(
                Score(
                    reps = 0,
                    weight = 0,
                    workoutId = workoutId,
                    dayWorkoutId = dayWorkoutId
                )
            )
        }

    }

    fun updateScore(score: Score) {
        viewModelScope.launch {
            scoreRepository.upsert(
                score
            )
        }
    }

    fun deleteScore(score: Score) {
        viewModelScope.launch {
            scoreRepository.delete(score)
        }
    }



}