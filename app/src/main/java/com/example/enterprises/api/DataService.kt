package com.example.enterprises.api

import com.example.enterprises.domains.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @POST("users/auth/sign_in")
    fun recoverVerifyLogin(@Body userRequest: UserRequest): Call<ResponseBody>
}