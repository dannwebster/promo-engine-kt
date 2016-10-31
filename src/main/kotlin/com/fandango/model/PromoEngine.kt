package com.fandango.model

import com.fandango.factories.PromoFactory
import com.fandango.data.RuleDao

class PromoEngine(val ruleDao: RuleDao, val promoFactory: PromoFactory) {

    fun getPromoByCampaignId(campaignId: String) =
        promoFactory.createPromo(campaignId, ruleDao.recordsByCampaignId(campaignId))


}