package paul.vickers.built4life.ui.showWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import paul.vickers.built4life.model.WorkoutWithMaxScore
import paul.vickers.built4life.repository.WorkoutRepository

class WorkoutsViewModel(
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {


    val workouts: StateFlow<List<WorkoutWithMaxScore>> = workoutRepository.workoutsWithMaxScores
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}