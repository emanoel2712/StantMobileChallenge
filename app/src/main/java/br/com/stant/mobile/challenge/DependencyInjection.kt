package br.com.stant.mobile.challenge

import br.com.stant.mobile.challenge.data.api.APIClient
import br.com.stant.mobile.challenge.data.api.APIResource
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

val moviesUseCaseModule = module {

    single<GetMoviesUseCase> {
        GetMoviesUseCaseImpl(get())
    }
}

val moviesViewModelModule = module {

    viewModel {
        MoviesViewModel(get())
    }
}



