package br.com.stant.mobile.challenge.domain.usecases

import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.presenter.resource.utils.Resource

interface GetMoviesUseCase {

    suspend operator fun invoke(): Resource<List<Movie>>
}