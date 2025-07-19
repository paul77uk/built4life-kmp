package paul.vickers.built4life.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.database.DriverFactory
import paul.vickers.built4life.repository.ScoreRepository
import paul.vickers.built4life.repository.ScoreRepositoryImpl
import paul.vickers.built4life.repository.WorkoutRepository
import paul.vickers.built4life.repository.WorkoutRepositoryImpl
import paul.vickers.built4life.ui.addScore.AddScoreViewModel
import paul.vickers.built4life.ui.deleteWorkouts.DeleteWorkoutViewModel
import paul.vickers.built4life.ui.showWorkouts.WorkoutsViewModel
import paul.vickers.built4life.ui.upsertWorkout.UpsertWorkoutViewModel

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

    viewModelOf(::WorkoutsViewModel)
    viewModelOf(::UpsertWorkoutViewModel)
    viewModelOf(::DeleteWorkoutViewModel)
    viewModelOf(::AddScoreViewModel)

}