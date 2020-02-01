package com.example.cleanmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cleanmvvm.data.network.repositories.UserNetworkRepository
import com.example.cleanmvvm.databinding.ActivityMainBinding
import com.example.cleanmvvm.domain.usecases.GitHubUserUseCase
import com.example.cleanmvvm.presentation.search.viewmodel.SearchReposViewModel
import com.example.cleanmvvm.presentation.search.viewmodel.SearchReposViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        activityMainBinding.reposViewModel= ViewModelProviders.of(this,
            SearchReposViewModelFactory(
                GitHubUserUseCase(UserNetworkRepository())
            )
            )
            .get(SearchReposViewModel::class.java)

    }
}
