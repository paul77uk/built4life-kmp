package paul.vickers.built4life.ui.showPrograms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import paul.vickers.built4life.model.Program
import paul.vickers.built4life.repository.ProgramRepository

class ProgramsViewModel(
    private val programRepository: ProgramRepository
): ViewModel() {

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog

    private val _programToDelete = MutableStateFlow<Program?>(null)
    val programToDelete: StateFlow<Program?> = _programToDelete


    val programs: StateFlow<List<Program>> = programRepository.programs
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun setProgram(program: Program) {
        _programToDelete.value = program
    }


    fun openDeleteDialog() {
        _showDeleteDialog.value = true
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun deleteProgram() {
        viewModelScope.launch {
            programToDelete.value?.let { programRepository.delete(it) }
        }
    }

}