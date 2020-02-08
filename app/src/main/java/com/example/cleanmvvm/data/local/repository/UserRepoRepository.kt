package com.example.cleanmvvm.data.local.repository

import com.example.cleanmvvm.data.local.dao.UserRepoDao
import com.example.cleanmvvm.data.local.entity.UserRepo

class UserRepoRepository (val userRepoDao: UserRepoDao){

    val allRepo = userRepoDao.getUserRepoLiveData()

    suspend fun insert(userRepo: UserRepo){
        userRepoDao.insertUserRepo(userRepo)
    }

    suspend fun getRepoByName(name:String):List<UserRepo>?{
        return userRepoDao.getUserByName(name)
    }
}