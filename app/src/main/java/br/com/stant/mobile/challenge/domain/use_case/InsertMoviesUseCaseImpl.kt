package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.data.model.Result
import br.com.stant.mobile.challenge.data.repository.MoviesRepository

class InsertMoviesUseCaseImpl(private var moviesRepository: MoviesRepository): InsertMoviesUseCase {

    override suspend fun invoke(movies: List<Result>) {
        moviesRepository.insertMovies(movies)
    }
}