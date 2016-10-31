package com.fandango.model

import java.math.BigDecimal

enum class RuleType {
    PREDICATE, ACTION
}
enum class RecordType(val ruleType: RuleType) {
    DOLLARS_OFF(RuleType.ACTION),

    THEATER_ID(RuleType.PREDICATE),
    MOVIE_ID(RuleType.PREDICATE),
    IS_VIP(RuleType.PREDICATE)
}

data class RuleRecord(
        val type: RecordType,
        val movieId: String?=null,
        val theaterId: String?=null,
        val discount: BigDecimal?=null)