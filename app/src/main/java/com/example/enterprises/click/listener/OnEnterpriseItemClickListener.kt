package com.example.enterprises.click.listener

import com.example.enterprises.domains.enterprise.EnterpriseResponse

interface OnEnterpriseItemClickListener {
    fun onClick(enterpriseResponse: EnterpriseResponse)
}