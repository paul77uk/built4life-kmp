package paul.vickers.built4life.ui.showPrograms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import paul.vickers.built4life.model.Day
import paul.vickers.built4life.model.Program
import paul.vickers.built4life.model.ProgramsWithDays
import paul.vickers.built4life.repository.DayRepository
import paul.vickers.built4life.repository.ProgramRepository

class ProgramsViewModel(
    private val programRepository: ProgramRepository,
    private val dayRepository: DayRepository
) : ViewModel() {

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog

    private val _showDays = MutableStateFlow(false)
    val showDays: StateFlow<Boolean> = _showDays

    private val _programToDelete = MutableStateFlow<Program?>(null)
    val programToDelete: StateFlow<Program?> = _programToDelete

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


    fun openDeleteDialog() {
        _showDeleteDialog.value = true
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteProgram(program: Program) {
        viewModelScope.launch {
            programRepository.delete(program)
        }
        dismissDeleteDialog()
    }

//    fun toggleShowDays(program: Program) {
//            getDaysByProgramId(program.id!!)
//    }
}

