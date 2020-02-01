package com.example.cleanmvvm.domain.repositories

import com.example.cleanmvvm.data.network.models.response.GithubReponse
import io.reactivex.Single

interface UserRepository {

    fun getUserRepos(user:String): Single<List<GithubReponse>>
}