package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.model.Result
import br.com.stant.mobile.challenge.domain.model.toResultDto

class InsertMoviesUseCaseImpl(private var moviesRepository: MoviesRepository): InsertMoviesUseCase {

    override suspend fun invoke(movies: List<Result>) {
        val movies = movies.map {
            it.toResultDto()
        }

        moviesRepository.insertMovies(movies)
    }
}