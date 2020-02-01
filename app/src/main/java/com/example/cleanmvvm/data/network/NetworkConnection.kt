package com.example.cleanmvvm.data.network

import com.example.cleanmvvm.data.network.services.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASEURL="https://api.github.com/"
class NetworkConnection {


    fun getNetworkConnection():GitHubService{

        //interceptor es solo para mostrar las peticiones
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =HttpLoggingInterceptor.Level.BODY

        val client =OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        //objeto Retrofit que consultara a GithubService
        //esta configurado para convertir los objetos con Gson
        // la respuesta sera recibida con RXJava
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


        return retrofit.create<GitHubService>(GitHubService::class.java)
    }
}