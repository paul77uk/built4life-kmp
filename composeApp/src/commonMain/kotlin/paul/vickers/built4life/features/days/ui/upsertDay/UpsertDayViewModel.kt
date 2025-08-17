package paul.vickers.built4life.features.days.ui.upsertDay

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import paul.vickers.built4life.features.days.model.Day
import paul.vickers.built4life.features.days.repository.DayRepository
import paul.vickers.built4life.navigation.Routes

class UpsertDayViewModel(
    private val dayRepository: DayRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val dayId = savedStateHandle.toRoute<Routes.UpsertDayScreen>().dayId
    val dayTitle = savedStateHandle.toRoute<Routes.UpsertDayScreen>().dayTitle

    val programId = savedStateHandle.toRoute<Routes.UpsertDayScreen>().programId

    private val _dayTitleInput = MutableStateFlow(dayTitle ?: "")
    val dayTitleInput: StateFlow<String> = _dayTitleInput.asStateFlow()

    fun onTxtChange(newTxt: String) {
        _dayTitleInput.value = newTxt
    }

    fun upsertDay() {
        viewModelScope.launch {
            programId?.let {
                dayRepository.upsert(
                    Day(
                        id = dayId,
                        title = _dayTitleInput.value,
                        programId = it
                    )
                )
            }
        }
    }

}



