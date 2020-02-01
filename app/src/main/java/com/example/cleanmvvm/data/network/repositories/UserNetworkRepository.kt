package com.example.cleanmvvm.data.network.repositories

import com.example.cleanmvvm.data.network.NetworkConnection
import com.example.cleanmvvm.data.network.models.response.GithubReponse
import com.example.cleanmvvm.domain.repositories.UserRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserNetworkRepository:UserRepository {

    override fun getUserRepos(user: String): Single<List<GithubReponse>> {
        return NetworkConnection().getNetworkConnection()
            .getUserRepo(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}