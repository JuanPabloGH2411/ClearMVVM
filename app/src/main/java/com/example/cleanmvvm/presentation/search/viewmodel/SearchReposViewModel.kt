package com.example.cleanmvvm.presentation.search.viewmodel


import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvm.data.local.RepoRoomDatabase
import com.example.cleanmvvm.data.local.entity.UserRepo
import com.example.cleanmvvm.data.local.repository.UserRepoRepository
import com.example.cleanmvvm.data.network.models.response.GithubReponse
import com.example.cleanmvvm.domain.model.Repo
import com.example.cleanmvvm.domain.repositories.UserRepository
import com.example.cleanmvvm.domain.usecases.GitHubUserUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchReposViewModel(
    val userRepository: GitHubUserUseCase,
    private val repoRoomDatabase: RepoRoomDatabase
):ViewModel(){


    private val compositeDisposable:CompositeDisposable = CompositeDisposable()
    val repoMutableLiveData = MutableLiveData<List<Repo>>()

    val repoMediator= MediatorLiveData<List<UserRepo>>()

    val userInput =ObservableField<String>("")

    val userRepoRepository =UserRepoRepository(repoRoomDatabase.userRepoDao())

    fun getUserRepos(user:String) {
        compositeDisposable.add(
            userRepository.getUserRepos(user).subscribe ({
                val repoList =ArrayList<Repo>()
                it.forEach {
                    insertdata(it)
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
    
    fun getDataRepoFromMediator(){
        userRepoRepository.allRepo?.let {
            repoMediator.addSource(it,{
                //datos de la base de datos.
             it.forEach{
                 println("MediatorLiveData ${it.name}")
             }
            })
        }
    }


    fun insertdata(githubReponse: GithubReponse)=viewModelScope.launch {
        // cuando muchos viewmodel interactuen (GlobalScope)
        userRepoRepository.insert(
            UserRepo(
                description = githubReponse.description.orEmpty(),
                name = githubReponse.name.orEmpty(),
                fullName = githubReponse.full_name.orEmpty(),
                url = githubReponse.url.orEmpty()
            )
        )

        /*
        withContext(Dispatchers.Main){
            activity,fragment, ui en general
        }


         */


    }

    //jakeWharton


}