package br.com.stant.mobile.challenge

import br.com.stant.mobile.challenge.data.api.APIClient
import br.com.stant.mobile.challenge.data.api.APIResource
import org.koin.dsl.module


val apiModule = module {

    single {
        APIClient().createService(APIResource::class.java)
    }
}