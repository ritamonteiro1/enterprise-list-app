package com.example.enterprises.domains.enterprise

import com.google.gson.annotations.SerializedName

data class EnterpriseResponse(
    @SerializedName("enterprise_name") val enterpriseName: String? = null,
    val photo: String? = null,
    val description: String? = null,
    val country: String? = null,
    @SerializedName("enterprise_type") val enterpriseTypeResponse: EnterpriseTypeResponse? = null
)
