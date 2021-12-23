package br.com.stant.mobile.challenge.domain.usecases

import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.model.Movie
import br.com.stant.mobile.challenge.presenter.resource.utils.Resource

class GetMoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : GetMoviesUseCase {

    override suspend fun invoke(): Resource<List<Movie>> {
        return moviesRepository.getMovies()
    }
}