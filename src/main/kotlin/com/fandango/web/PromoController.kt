package com.fandango.web

import com.fandango.data.PromoDao
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PromoController(val promoDao: PromoDao) {

    @RequestMapping("/promo/{promoId}")
    fun getPromoById(@PathVariable("promoId") promoId: String) = promoDao.getPromoById(promoId)

}
