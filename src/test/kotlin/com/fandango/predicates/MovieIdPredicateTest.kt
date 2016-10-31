package com.fandango.predicates

import com.fandango.model.Cart
import com.fandango.model.RecordType
import com.fandango.model.RuleRecord
import org.junit.Test
import kotlin.test.assertEquals

class MovieIdPredicateTest {

    @Test(expected = IllegalArgumentException::class) fun noMovieIdRowShouldThrowExcpetion() {
        MovieIdPredicate.fromRecord(RuleRecord(type = RecordType.MOVIE_ID))
    }

    @Test(expected = IllegalArgumentException::class) fun nonMovieRowShouldCauseException() {
        MovieIdPredicate.fromRecord(RuleRecord(type = RecordType.IS_VIP))
    }

    @Test fun shouldBeBuiltFromRow() {
        assertEquals(MovieIdPredicate("1"), MovieIdPredicate.fromRecord(RuleRecord(type = RecordType.MOVIE_ID, movieId = "1")))
    }

    @Test fun shouldMatchMovieId() {
        assertEquals(true, MovieIdPredicate("1")(Cart(movieId = "1")))
        assertEquals(false, MovieIdPredicate("2")(Cart(movieId = "1")))
        assertEquals(false, MovieIdPredicate("2")(Cart(movieId = null)))
    }
}