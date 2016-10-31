package com.fandango.predicates

import com.fandango.model.*

data class TheaterIdPredicate(val theaterId: String): Predicate() {
    override operator fun invoke(cart: Cart): Boolean = cart.theaterId == theaterId

    companion object: PredicateBuilder {
        override val requiredType = RecordType.THEATER_ID
        override val composition = ::or

        override fun fromRecord(ruleRecord: RuleRecord): Predicate =
                if (!isRequiredType(ruleRecord))
                    throw IllegalArgumentException("not TheaterId: $ruleRecord")
                else if (ruleRecord.theaterId == null)
                    throw IllegalArgumentException("no theater id: $ruleRecord")
                else
                    TheaterIdPredicate(ruleRecord.theaterId)
    }
}
