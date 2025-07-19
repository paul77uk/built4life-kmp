package paul.vickers.built4life.ui.showWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import paul.vickers.built4life.model.WorkoutsWithScores
import paul.vickers.built4life.repository.WorkoutRepository

class WorkoutsViewModel(
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    val workouts: StateFlow<List<WorkoutsWithScores>> = workoutRepository.workoutsWithScores
        .map { it }
        .stateIn(
            scope = viewModelScope, // Use viewModelScope for coroutine lifecycle management
            started = SharingStarted.Companion.WhileSubscribed(5000), // Keep the flow active while subscribed (with a 5s grace period)
            initialValue = emptyList() // Provide an initial empty list
        )
}