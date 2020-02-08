package com.example.cleanmvvm.presentation.search.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanmvvm.R
import com.example.cleanmvvm.data.local.RepoRoomDatabase
import com.example.cleanmvvm.data.network.repositories.UserNetworkRepository
import com.example.cleanmvvm.databinding.ActivityMainBinding
import com.example.cleanmvvm.domain.model.Repo
import com.example.cleanmvvm.domain.usecases.GitHubUserUseCase
import com.example.cleanmvvm.presentation.search.adapter.ReposAdapter
import com.example.cleanmvvm.presentation.search.viewmodel.SearchReposViewModel
import com.example.cleanmvvm.presentation.search.viewmodel.SearchReposViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        activityMainBinding.reposViewModel= ViewModelProviders.of(this,
            SearchReposViewModelFactory(
                GitHubUserUseCase(UserNetworkRepository()),
                RepoRoomDatabase.getDatabase(this)
            )
            )
            .get(SearchReposViewModel::class.java)

        /*
        activityMainBinding.reposViewModel?.repoMutableLiveData?.observe(
            this,
            Observer{ repolist->
            activityMainBinding?.rvRepos?.apply {
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=ReposAdapter(repolist)
            }
        })

         */

            activityMainBinding.reposViewModel?.userRepoRepository?.allRepo?.observe(
                this,
                Observer { repoDBlist ->
                    val repoList = ArrayList<Repo>()
                    repoDBlist.forEach {
                        repoList.add(
                            Repo(
                                url = it.url.orEmpty(),
                                name = it.name.orEmpty(),
                                description = it.description.orEmpty()
                            )
                        )
                    }

                    activityMainBinding.rvRepos?.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = ReposAdapter(repoList)
                    }


                }

            )

        activityMainBinding.reposViewModel?.repoMutableLiveData
            ?.observe(this, Observer {
                activityMainBinding.rvRepos?.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ReposAdapter(it)
                }
            })
    }
}
