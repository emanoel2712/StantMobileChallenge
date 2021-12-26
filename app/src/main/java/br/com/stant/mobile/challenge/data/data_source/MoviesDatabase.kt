package br.com.stant.mobile.challenge.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.stant.mobile.challenge.data.data_source.dao.ResultDao
import br.com.stant.mobile.challenge.data.model.Result

@Database(entities = [Result::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val resultDao: ResultDao
}