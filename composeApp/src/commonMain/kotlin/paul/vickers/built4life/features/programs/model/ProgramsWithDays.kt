package paul.vickers.built4life.features.programs.model

import paul.vickers.built4life.features.days.model.Day

data class ProgramsWithDays(
    val program: Program,
    val days: List<Day>? = emptyList()
)