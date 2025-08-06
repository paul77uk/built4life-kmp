package paul.vickers.built4life.ui.upsertWorkout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.repository.WorkoutRepository
import paul.vickers.built4life.ui.navigation.Routes

class UpsertWorkoutViewModel(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val workoutId = savedStateHandle.toRoute<Routes.UpsertWorkoutScreen>().workoutId
   val workoutTitle = savedStateHandle.toRoute<Routes.UpsertWorkoutScreen>().workoutTitle

    private val creationDate = savedStateHandle.toRoute<Routes.UpsertWorkoutScreen>().creationDate

    private val _workoutTitleInput = MutableStateFlow(workoutTitle ?: "")
    val workoutTitleInput: StateFlow<String> = _workoutTitleInput.asStateFlow()

    private val _eliteLevelInput = MutableStateFlow("")
    val eliteLevelInput: StateFlow<String> = _eliteLevelInput.asStateFlow()

    init {
        if (workoutId != null) {
            getWorkoutById(workoutId)
        }
    }

    fun onTxtChange(newTxt: String) {
        _workoutTitleInput.value = newTxt
    }

    fun onEliteLevelChange(newTxt: String) {
        _eliteLevelInput.value = newTxt
    }

    fun getWorkoutById(id: Long) {
        viewModelScope.launch {
          _eliteLevelInput.value = (workoutRepository.getById(id)?.eliteLevel ?: "").toString()
        }
    }


    // if workout is null, then we are adding a new workout
    // if workout is not null, then we are updating an existing workout
    // so we set the txt to the workouts title
    fun upsertWorkout() {
        if (_workoutTitleInput.value.isNotEmpty()) {
            viewModelScope.launch {
                workoutRepository.upsert(
                    Workout(
                        id = workoutId,
                        title = _workoutTitleInput.value,
                        eliteLevel = _eliteLevelInput.value.toLongOrNull(),
                    )
                )
            }
        }
    }
}