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

    private val workoutId = savedStateHandle.toRoute<Routes.UpsertWorkoutScreen>().workoutId

    private val _editingWorkoutItem = MutableStateFlow<Workout?>(null)
    val editingWorkoutItem: StateFlow<Workout?> = _editingWorkoutItem.asStateFlow()

    private val _workoutTitleInput = MutableStateFlow("")
    val workoutTitleInput: StateFlow<String> = _workoutTitleInput.asStateFlow()

    init {
        workoutId?.let { getWorkoutById(it) }
    }

    fun onTxtChange(newTxt: String) {
        _workoutTitleInput.value = newTxt
    }

    fun getWorkoutById(id: Long) {
        viewModelScope.launch {
            val workout = workoutRepository.getById(id)
            _editingWorkoutItem.value = workout
            _workoutTitleInput.value = workout?.title.toString()
        }
    }
    // if todoItem is null, then we are adding a new todo
    // if todoItem is not null, then we are updating an existing todo
    // so we set the txt to the todoItem's title
    fun upsertWorkout() {
        if (_workoutTitleInput.value.isNotEmpty()) {
            viewModelScope.launch {
               val workout = _editingWorkoutItem.value
                workoutRepository.upsert(
                    if (workout != null) Workout(
                        id = workout.id,
                        title = _workoutTitleInput.value
                    ) else Workout(
                        title = _workoutTitleInput.value
                    )
                )
            }
        }
    }
}