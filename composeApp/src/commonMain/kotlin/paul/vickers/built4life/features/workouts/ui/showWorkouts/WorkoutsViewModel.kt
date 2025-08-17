package paul.vickers.built4life.features.workouts.ui.showWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import paul.vickers.built4life.features.workouts.model.WorkoutWithMaxScore
import paul.vickers.built4life.features.workouts.repository.WorkoutRepository

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