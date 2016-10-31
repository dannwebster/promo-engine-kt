package com.fandango.predicates

import com.fandango.model.Cart
import com.fandango.model.RecordType
import com.fandango.model.RuleRecord
import org.junit.Test
import kotlin.test.assertEquals

class TheaterIdPredicateTest {

    @Test(expected = IllegalArgumentException::class) fun noTheaterIdRowShouldThrowExcpetion() {
        TheaterIdPredicate.fromRecord(RuleRecord(type = RecordType.THEATER_ID))
    }

    @Test(expected = IllegalArgumentException::class) fun nonTheaterRowShouldCauseException() {
        TheaterIdPredicate.fromRecord(RuleRecord(type = RecordType.IS_VIP))
    }

    @Test fun shouldBeBuiltFromRow() {
        assertEquals(TheaterIdPredicate("1"), TheaterIdPredicate.fromRecord(RuleRecord(type = RecordType.THEATER_ID, theaterId = "1")))
    }

    @Test fun shouldMatchTheaterId() {
        assertEquals(true, TheaterIdPredicate("1")(Cart(theaterId = "1")))
        assertEquals(false, TheaterIdPredicate("2")(Cart(theaterId = "1")))
        assertEquals(false, TheaterIdPredicate("2")(Cart(theaterId = null)))
    }
}