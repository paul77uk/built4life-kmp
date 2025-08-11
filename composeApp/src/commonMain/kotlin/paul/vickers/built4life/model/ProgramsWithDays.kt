package paul.vickers.built4life.model

data class ProgramsWithDays(
    val program: Program,
    val days: List<Day>? = emptyList()
)