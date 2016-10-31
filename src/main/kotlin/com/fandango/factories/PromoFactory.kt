package com.fandango.factories

import com.fandango.model.Promo
import com.fandango.model.RuleRecord
import com.fandango.model.RuleType

class PromoFactory(val predicateFactory: PredicateFactory, val actionFactory: ActionFactory) {

    fun createPromo(promoId: String, ruleRecords: Iterable<RuleRecord>): Promo {
        val recordMap = segmentActionsFromPredicates(ruleRecords)
        val predicate = createPredicate( recordMap.getOrDefault(RuleType.PREDICATE, emptyList()) )
        val action = createAction( recordMap.getOrDefault(RuleType.ACTION, emptyList()) )
        return Promo(promoId, predicate, action)
    }

    fun createPredicate(ruleRecords: Iterable<RuleRecord>) = predicateFactory.createCompositePredicate(ruleRecords)

    fun createAction(ruleRecords: Iterable<RuleRecord>) = actionFactory.createAction(ruleRecords)

    fun segmentActionsFromPredicates(ruleRecords: Iterable<RuleRecord>): Map<RuleType, List<RuleRecord>> =
            ruleRecords.groupBy { record -> record.type.ruleType }

}