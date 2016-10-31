package com.fandango.predicates

import com.fandango.model.*

class VipPredicate: Predicate() {
    override operator fun invoke(cart: Cart) = cart.isVip

    companion object: PredicateBuilder {
        val instance = VipPredicate()
        override val requiredType = RecordType.IS_VIP
        override val composition = ::and

        override fun fromRecord(ruleRecord: RuleRecord): Predicate =
                if (isRequiredType(ruleRecord))
                    instance
                else
                    throw IllegalArgumentException("non-IS_VIP")
    }
}