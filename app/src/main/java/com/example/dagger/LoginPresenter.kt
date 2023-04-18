package com.example.dagger

import android.util.Log
import javax.inject.Inject

/**
 *
 */
class LoginPresenter @Inject constructor(
    private val loginRepository: LoginRepository
) {
    fun getData(){
        loginRepository.getData()
    }
}
class LoginRepository @Inject constructor(
    private val localDatasource: LoginLocalDatasource,
    private val loginRemoteDatasource: LoginRemoteDatasource
){
    fun getData() {
        localDatasource.getLocalData()
        loginRemoteDatasource.getRemoteData()
    }
}
class LoginLocalDatasource @Inject constructor() {
    fun getLocalData() {
        Log.d("getData","本地数据来啦")
    }
}
class LoginRemoteDatasource @Inject constructor() {
    fun getRemoteData() {
        Log.d("getData","远程数据来啦")
    }
}