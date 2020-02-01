package com.example.cleanmvvm.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanmvvm.data.local.RepoRoomDatabase
import com.example.cleanmvvm.domain.usecases.GitHubUserUseCase

class SearchReposViewModelFactory(
    val userRepository: GitHubUserUseCase,
    private val repoRoomDatabase: RepoRoomDatabase
    ):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return SearchReposViewModel(userRepository,repoRoomDatabase) as T
    }
}