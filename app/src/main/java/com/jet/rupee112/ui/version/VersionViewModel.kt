package com.jet.rupee112.ui.version

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jet.rupee112.api.ApiService
import com.jet.rupee112.api.DataState
import com.jet.rupee112.api.HomeRepository
import com.jet.rupee112.api.apiService
import com.jet.rupee112.modal.AppVersionRequest

import com.jet.rupee112.modal.Demo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VersionViewModel :ViewModel() {
   private val _versionCheck = MutableStateFlow<DataState<Demo>>(DataState.Loading)
    val versionCheck:StateFlow<DataState<Demo>> = _versionCheck

    private val _version = MutableStateFlow<DataState<Demo>>(DataState.Loading)
    val version:StateFlow<DataState<Demo>> = _version

//    fun checkApiVersion(){
//        viewModelScope.launch {
//            try {

//                val data = apiService.version()
//                _version.value = DataState.Success(data)
//            }catch (e:Exception){
//               _version.value = DataState.Error("Network error occurred")
//            }
//
//        }
//    }

    fun checkVersionApp(apiService: ApiService,param: AppVersionRequest){
        viewModelScope.launch {
        try {
            val data =  HomeRepository.version(apiService,param)
            _versionCheck.value = DataState.Success(data)

        }catch (e:Exception){
            _versionCheck.value = DataState.Error(e.localizedMessage?:"Some Error occur")
        }
        }
    }

}