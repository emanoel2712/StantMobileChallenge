package br.com.stant.mobile.challenge.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.stant.mobile.challenge.data.model.ResultDto

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: List<ResultDto>)

    @Query("SELECT * FROM result")
    suspend fun getMovies(): List<ResultDto>

}