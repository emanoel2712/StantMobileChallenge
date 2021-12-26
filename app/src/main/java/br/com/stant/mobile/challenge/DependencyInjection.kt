package br.com.stant.mobile.challenge

import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.stant.mobile.challenge.data.data_source.APIClient
import br.com.stant.mobile.challenge.data.data_source.APIResource
import br.com.stant.mobile.challenge.data.data_source.MoviesDatabase
import br.com.stant.mobile.challenge.data.data_source.dao.ResultDao
import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.use_case.GetMoviesUseCase
import br.com.stant.mobile.challenge.domain.use_case.GetMoviesUseCaseImpl
import br.com.stant.mobile.challenge.presentation.viewmodel.MoviesViewModel
import br.com.stant.mobile.challenge.resource.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// MARK: API

val apiModule = module {

    single {
        APIClient().createService(APIResource::class.java)
    }
}

// MARK: Database

val databaseModule = module {

    single<MoviesDatabase> {
        Room.databaseBuilder(androidApplication(), MoviesDatabase::class.java, Constants.DATABASE_NAME).build()
    }
}

// MARK: Dao

val resultDao = module {

    single<ResultDao> {
        val database = get<MoviesDatabase>()
        database.getResultDao()
    }
}


// MARK: Repositories

val moviesRepositoryModule = module {

    single {
        MoviesRepository(get(), get())
    }
}

// MARK: Use Cases

val moviesUseCaseModule = module {

    single<GetMoviesUseCase> {
        GetMoviesUseCaseImpl(get())
    }
}

// MARK: View Models

val moviesViewModelModule = module {

    viewModel {
        MoviesViewModel(get())
    }
}



