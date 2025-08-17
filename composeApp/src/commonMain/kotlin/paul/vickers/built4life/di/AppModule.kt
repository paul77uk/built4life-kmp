package paul.vickers.built4life.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.database.DriverFactory
import paul.vickers.built4life.features.days.repository.DayRepository
import paul.vickers.built4life.features.days.repository.DayRepositoryImpl
import paul.vickers.built4life.features.days.repository.DayWorkoutRepository
import paul.vickers.built4life.features.days.repository.DayWorkoutRepositoryImpl
import paul.vickers.built4life.features.days.ui.dayScreen.DayScreenViewModel
import paul.vickers.built4life.features.days.ui.upsertDay.UpsertDayViewModel
import paul.vickers.built4life.features.programs.repository.ProgramRepository
import paul.vickers.built4life.features.programs.repository.ProgramRepositoryImpl
import paul.vickers.built4life.features.programs.ui.showPrograms.ProgramsViewModel
import paul.vickers.built4life.features.programs.ui.upsertProgram.UpsertProgramViewModel
import paul.vickers.built4life.features.scores.repository.ScoreRepository
import paul.vickers.built4life.features.scores.repository.ScoreRepositoryImpl
import paul.vickers.built4life.features.scores.ui.addScore.AddScoreViewModel
import paul.vickers.built4life.features.workouts.repository.WorkoutRepository
import paul.vickers.built4life.features.workouts.repository.WorkoutRepositoryImpl
import paul.vickers.built4life.features.workouts.ui.deleteWorkouts.DeleteWorkoutViewModel
import paul.vickers.built4life.features.workouts.ui.showWorkouts.WorkoutsViewModel
import paul.vickers.built4life.features.workouts.ui.upsertWorkout.UpsertWorkoutViewModel


val appModule = module {
    val sqlDriver = DriverFactory().createDriver()
    val database = B4LDatabase.invoke(sqlDriver)
    single {
//        if (sqlDriver == null) {
//            WebWorkoutRepositoryImpl(Settings())
//        } else {
        WorkoutRepositoryImpl(database)
//        }
    }.bind<WorkoutRepository>()

    single {
        ScoreRepositoryImpl(database)
    }.bind<ScoreRepository>()

    single {
        ProgramRepositoryImpl(database)
    }.bind<ProgramRepository>()

    single {
        DayRepositoryImpl(database)
    }.bind<DayRepository>()

    single {
        DayWorkoutRepositoryImpl(database)
    }.bind<DayWorkoutRepository>()


    viewModelOf(::WorkoutsViewModel)
    viewModelOf(::UpsertWorkoutViewModel)
    viewModelOf(::DeleteWorkoutViewModel)
    viewModelOf(::AddScoreViewModel)
    viewModelOf(::ProgramsViewModel)
    viewModelOf(::UpsertProgramViewModel)
    viewModelOf(::UpsertDayViewModel)
    viewModelOf(::DayScreenViewModel)



}