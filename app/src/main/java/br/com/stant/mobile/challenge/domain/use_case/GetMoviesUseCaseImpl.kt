package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.resource.utils.Resource

class GetMoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : GetMoviesUseCase {

    override suspend fun invoke(page: Int?): Resource<Movie> {
        return moviesRepository.getMovies(page)
    }
}