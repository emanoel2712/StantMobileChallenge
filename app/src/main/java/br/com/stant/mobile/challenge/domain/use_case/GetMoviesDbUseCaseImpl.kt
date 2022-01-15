package br.com.stant.mobile.challenge.domain.use_case

import br.com.stant.mobile.challenge.data.repository.MoviesRepository
import br.com.stant.mobile.challenge.domain.model.Result

class GetMoviesDbUseCaseImpl(private val moviesRepository: MoviesRepository) : GetMoviesDbUseCase {

    override suspend fun invoke(): List<Result> {
        return moviesRepository.getMoviesDB()
    }
}