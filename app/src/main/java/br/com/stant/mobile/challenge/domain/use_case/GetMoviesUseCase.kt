package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.resource.utils.Resource

interface GetMoviesUseCase {

    suspend operator fun invoke(page : Int? = 1): Resource<Movie>
}