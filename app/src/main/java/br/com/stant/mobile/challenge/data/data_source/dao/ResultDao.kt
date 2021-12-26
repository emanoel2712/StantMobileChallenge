package br.com.stant.mobile.challenge.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.stant.mobile.challenge.data.model.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: List<Result>)

    @Query("SELECT * FROM result")
    suspend fun getMovies(): List<Result>

}