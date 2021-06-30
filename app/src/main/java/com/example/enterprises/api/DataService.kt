package com.example.enterprises.api

import com.example.enterprises.constants.Constants
import com.example.enterprises.domains.enterprise.EnterpriseListResponse
import com.example.enterprises.domains.user.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @POST("users/auth/sign_in")
    fun recoverVerifyLogin(@Body userRequest: UserRequest): Call<ResponseBody>

    @GET("enterprises")
    fun recoverEnterpriseListResponse(
        @Query(Constants.ENTERPRISE_NAME) enterpriseName: String?,
        @Header("access-token") accessToken: String?,
        @Header("client") client: String?,
        @Header("uid") uid: String?
    ): Call<EnterpriseListResponse?>
}