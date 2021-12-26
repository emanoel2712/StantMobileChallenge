package br.com.stant.mobile.challenge.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.stant.mobile.challenge.data.data_source.dao.ResultDao
import br.com.stant.mobile.challenge.data.model.Result
import br.com.stant.mobile.challenge.resource.utils.Constants

@Database(entities = [Result::class], version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context: Context): MoviesDatabase {
            val tempInstance = INSTANCE

            tempInstance?.let {
                return it
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}