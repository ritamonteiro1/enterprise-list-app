package com.example.enterprises.domains.enterprise

import java.io.Serializable

data class EnterpriseListResponse(val enterprises: List<EnterpriseResponse>): Serializable
