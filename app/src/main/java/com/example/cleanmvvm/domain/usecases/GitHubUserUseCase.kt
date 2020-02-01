package com.example.cleanmvvm.domain.usecases

import com.example.cleanmvvm.data.network.models.response.GithubReponse
import com.example.cleanmvvm.domain.repositories.UserRepository
import io.reactivex.Observable
import java.util.*

class GitHubUserUseCase(private val userRepository: UserRepository) {

    fun getUserRepos(user:String):Observable<List<GithubReponse>>{
        return userRepository.getUserRepos(user)
            .toObservable()
            .doOnNext {
                if(it.isNotEmpty()){
                    return@doOnNext
                }
                else{
                    throw  Exception("Error con los datos")
                }

            }.doOnError {
                if(it.message.orEmpty().isNotEmpty()){
                    throw Exception("No se establece Conexión a internet")
                }else{
                    return@doOnError
                }
            }
    }
}