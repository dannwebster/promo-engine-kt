package com.fandango.predicates

import com.fandango.model.Cart
import com.fandango.model.RecordType
import com.fandango.model.RuleRecord
import org.junit.Test
import kotlin.test.assertEquals

class VipPredicateTest {
    @Test fun shouldBuildFromRecord() {
        assertEquals(VipPredicate.instance, VipPredicate.fromRecord(RuleRecord(type=RecordType.IS_VIP)) )
    }

    @Test(expected = IllegalArgumentException::class) fun nonVipRecordshouldThrowException() {
        VipPredicate.fromRecord(RuleRecord(type=RecordType.MOVIE_ID))
    }

    @Test fun shouldValidateVip() {
        assertEquals(true, VipPredicate()(Cart(isVip = true)))
        assertEquals(false, VipPredicate()(Cart(isVip = false)))
    }
}
