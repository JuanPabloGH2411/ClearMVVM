package com.example.cleanmvvm.data.network.services

import com.example.cleanmvvm.data.network.models.response.GithubReponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    fun getUserRepo(@Path ("user") user:String): Single<List<GithubReponse>>
}