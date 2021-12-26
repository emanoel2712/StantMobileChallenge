package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.domain.model.Result

interface InsertMoviesUseCase {

    suspend operator fun invoke(movies: List<Result>)
}