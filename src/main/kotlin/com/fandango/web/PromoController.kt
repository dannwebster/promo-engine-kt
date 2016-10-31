package com.fandango.web

import com.fandango.model.PromoEngine
import com.fandango.model.Promo
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class PromoController(val promoEngine: PromoEngine) {

    @RequestMapping("/campaign/{campaignId}")
    fun getPromo(@PathVariable("campaignId") campaignId: String): Promo = promoEngine.getPromoByCampaignId(campaignId)

    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/promo/{promoId}")
    fun applyPromo(campaignId: String): Promo = promoEngine.getPromoByCampaignId(campaignId)

}
