package com.example.cleanmvvm.presentation.search.viewmodel


import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanmvvm.domain.model.Repo
import com.example.cleanmvvm.domain.repositories.UserRepository
import com.example.cleanmvvm.domain.usecases.GitHubUserUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class SearchReposViewModel(
    val userRepository: GitHubUserUseCase
):ViewModel(){


    private val compositeDisposable:CompositeDisposable = CompositeDisposable()
    val repoMutableLiveData = MutableLiveData<List<Repo>>()

     val userInput =ObservableField<String>("")



    fun getUserRepos(user:String) {
        compositeDisposable.add(
            userRepository.getUserRepos(user).subscribe ({
                val repoList =ArrayList<Repo>()
                it.forEach {
                    repoList.add(
                        Repo(
                            url =it.url.orEmpty(),
                            name= it.full_name.orEmpty(),
                            description = it.description.orEmpty()
                            )
                    )


                    println("Repo Name : ${it.name.orEmpty()}")
                }
                repoMutableLiveData.value=repoList


            },{
                println("Error Message : $it.message")
            })

        )
    }

    fun onSearchClick(){

        getUserRepos(userInput.get().orEmpty())

    }

    //jakeWharton


}