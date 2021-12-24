package br.com.stant.mobile.challenge

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MobileChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MobileChallengeApplication)
            modules(listOf(apiModule, moviesRepositoryModule, moviesUseCaseModule, moviesViewModelModule))
        }
    }
}