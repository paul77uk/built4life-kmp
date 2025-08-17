package paul.vickers.built4life.features.workouts.ui.deleteWorkouts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.features.workouts.repository.WorkoutRepository
import paul.vickers.built4life.navigation.Routes

class DeleteWorkoutViewModel(
    private val workoutRepository: WorkoutRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(
) {

    val workoutId = savedStateHandle.toRoute<Routes.UpsertWorkoutScreen>().workoutId
    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    fun openDeleteDialog(){
//        _workoutToDelete.value = workout
        _showDeleteDialog.value = true
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteWorkout() {
        viewModelScope.launch { workoutId?.let { workoutRepository.delete(it) } }
    }
}