package com.jet.rupee112.api

object ApiController {
    fun getApi() : ApiService = ApiService.create()
}