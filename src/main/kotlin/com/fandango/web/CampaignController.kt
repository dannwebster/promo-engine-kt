package com.fandango.web

import com.fandango.model.Promo
import com.fandango.model.PromoEngine
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CampaignController(val promoEngine: PromoEngine) {

    @RequestMapping("/campaign/{campaignId}")
    fun getPromo(@PathVariable("campaignId") campaignId: String): Promo = promoEngine.getPromoByCampaignId(campaignId)
}