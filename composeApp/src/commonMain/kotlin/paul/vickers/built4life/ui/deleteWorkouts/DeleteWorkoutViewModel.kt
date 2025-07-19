package paul.vickers.built4life.ui.deleteWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.repository.WorkoutRepository

class DeleteWorkoutViewModel(
    private val workoutRepository: WorkoutRepository
): ViewModel(
) {

    private val _workoutToDelete = MutableStateFlow<Workout?>(null)
    val workoutToDelete: StateFlow<Workout?> = _workoutToDelete.asStateFlow()
    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    fun openDeleteDialog(workout: Workout){
        _workoutToDelete.value = workout
        _showDeleteDialog.value = true
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteWorkout() {
        viewModelScope.launch { workoutRepository.delete(workoutToDelete.value!!) }
    }
}