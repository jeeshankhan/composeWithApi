package com.jet.rupee112.api

import com.jet.rupee112.modal.AppVersionRequest

object HomeRepository {
    suspend fun version(api:ApiService,param: AppVersionRequest)= api.version(param)
}