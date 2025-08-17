package paul.vickers.built4life.features.programs.ui.showPrograms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import paul.vickers.built4life.features.days.model.Day
import paul.vickers.built4life.features.days.repository.DayRepository
import paul.vickers.built4life.features.programs.model.Program
import paul.vickers.built4life.features.programs.model.ProgramsWithDays
import paul.vickers.built4life.features.programs.repository.ProgramRepository

class ProgramsViewModel(
    private val programRepository: ProgramRepository,
    private val dayRepository: DayRepository
) : ViewModel() {

    private val _showProgramDeleteDialog = MutableStateFlow(false)
    val showProgramDeleteDialog: StateFlow<Boolean> = _showProgramDeleteDialog

    private val _showDayDeleteDialog = MutableStateFlow(false)
    val showDayDeleteDialog: StateFlow<Boolean> = _showDayDeleteDialog


    private val _showDays = MutableStateFlow(false)
    val showDays: StateFlow<Boolean> = _showDays

    private val _programToDelete = MutableStateFlow<Program?>(null)
    val programToDelete: StateFlow<Program?> = _programToDelete

    // In ProgramsViewModel
    private val _dayToDelete = MutableStateFlow<Day?>(null)
    val dayToDelete: StateFlow<Day?> = _dayToDelete.asStateFlow()

    // _showDayDeleteDialog remains as is

    private val _days = MutableStateFlow<List<Day>>(emptyList())
    val days: StateFlow<List<Day>> = _days


    val programs: StateFlow<List<ProgramsWithDays>> = programRepository.programsWithDays
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

//    fun getDaysByProgramId(programId: Long) {
//        viewModelScope.launch {
//            dayRepository.getDaysByProgramId(programId)
//                .collect { days ->
//                    _days.value = days
//                }
//        }
//    }

    fun setProgram(program: Program) {
        _programToDelete.value = program
    }


    fun openProgramDeleteDialog(program: Program) {
        _showProgramDeleteDialog.value = true
        _programToDelete.value = program
    }

    fun dismissProgramDeleteDialog() {
        _showProgramDeleteDialog.value = false
    }


    fun deleteProgram() {
        viewModelScope.launch {
            programRepository.delete(programToDelete.value!!)
        }
        dismissProgramDeleteDialog()
    }

    fun openDayDeleteDialog(day: Day) {
        _dayToDelete.value = day
        _showDayDeleteDialog.value = true
    }

    fun dismissDayDeleteDialog() {
        _showDayDeleteDialog.value = false
        _dayToDelete.value = null
        // Optionally clear _dayToDelete.value = null here or when confirmed
    }

    fun deleteSelectedDay() { // Renamed from deleteDay(Day)
        _dayToDelete.value?.let { day ->
            // ... your existing deletion logic for 'day' ...
            deleteDay(day) // Call your existing private or internal deleteDay
        }
        dismissDayDeleteDialog() // Dismiss after deletion
    }

    fun deleteDay(day: Day) {
        viewModelScope.launch {
            dayRepository.delete(day)
        }
    }


//    fun toggleShowDays(program: Program) {
//            getDaysByProgramId(program.id!!)
//    }
}

