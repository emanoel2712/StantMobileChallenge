package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.domain.model.Result

interface GetMoviesDbUseCase {

    suspend operator fun invoke(): List<Result>

}