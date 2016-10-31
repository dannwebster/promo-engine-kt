package com.fandango.model

import java.math.BigDecimal

data class Cart(
        val isVip: Boolean=false,
        val movieId: String?=null,
        val theaterId: String?=null,
        val value: BigDecimal = BigDecimal("0.0")
)
