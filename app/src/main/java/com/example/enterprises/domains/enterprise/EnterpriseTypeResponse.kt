package com.example.enterprises.domains.enterprise

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EnterpriseTypeResponse(
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String?
) : Serializable