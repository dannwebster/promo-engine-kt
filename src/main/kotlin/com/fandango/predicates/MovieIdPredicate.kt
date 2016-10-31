package com.fandango.predicates

import com.fandango.model.*

data class MovieIdPredicate(val movieId: String): Predicate() {
    override operator fun invoke(cart: Cart): Boolean = cart.movieId == movieId

    companion object: PredicateBuilder {
        override val requiredType = RecordType.MOVIE_ID
        override val composition = ::or

        override fun fromRecord(ruleRecord: RuleRecord): Predicate =
                if (!isRequiredType(ruleRecord))
                    throw IllegalArgumentException("not MovieId: $ruleRecord")
                else if (ruleRecord.movieId == null)
                    throw IllegalArgumentException("no movie id: $ruleRecord")
                else
                    MovieIdPredicate(ruleRecord.movieId)
    }
}
