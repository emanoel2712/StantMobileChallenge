package br.com.stant.mobile.challenge

import br.com.stant.mobile.challenge.data.api.APIClient
import br.com.stant.mobile.challenge.data.api.APIResource
import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.usecases.GetMoviesUseCase
import br.com.stant.mobile.challenge.domain.usecases.GetMoviesUseCaseImpl
import br.com.stant.mobile.challenge.presenter.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val apiModule = module {

    single {
        APIClient().createService(APIResource::class.java)
    }
}

// MARK: Repositories

val moviesRepositoryModule = module {

    single {
        MoviesRepository(get())
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



