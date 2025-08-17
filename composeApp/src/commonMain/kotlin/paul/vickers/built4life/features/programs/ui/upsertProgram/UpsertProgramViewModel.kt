package paul.vickers.built4life.features.programs.ui.upsertProgram

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.features.programs.model.Program
import paul.vickers.built4life.features.programs.repository.ProgramRepository
import paul.vickers.built4life.navigation.Routes

class UpsertProgramViewModel(
    private val programRepository: ProgramRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val programId = savedStateHandle.toRoute<Routes.UpsertProgramScreen>().programId
    val programTitle = savedStateHandle.toRoute<Routes.UpsertProgramScreen>().programTitle


    private val _programTitleInput = MutableStateFlow(programTitle ?: "")
    val programTitleInput: StateFlow<String> = _programTitleInput.asStateFlow()

    fun onTxtChange(newTxt: String) {
        _programTitleInput.value = newTxt
    }

    fun upsertProgram() {
        viewModelScope.launch {
            if (programId == null) {
                insertProgram()
            } else {
                updateProgram()
            }
        }
    }

    fun insertProgram() {
        viewModelScope.launch {
            programRepository.insert(
                Program(
                    title = _programTitleInput.value
                )
            )
        }
    }

    fun updateProgram() {
        viewModelScope.launch {
            programRepository.update(
                Program(
                    id = programId,
                    title = _programTitleInput.value
                )
            )
        }
    }

}