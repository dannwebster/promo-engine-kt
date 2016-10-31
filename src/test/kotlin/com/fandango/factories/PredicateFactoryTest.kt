package com.fandango.factories

import com.fandango.factories.PredicateFactory
import com.fandango.model.Cart
import com.fandango.model.RecordType
import com.fandango.model.RuleRecord
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by dwebster on 10/28/16.
 */
class PredicateFactoryTest {


    @Test fun multipleTheaterRecordsShouldAllowOneTheaterOrTheOther() {
        // given
        val rows = listOf(
                RuleRecord(type = RecordType.THEATER_ID, theaterId = "1"),
                RuleRecord(type = RecordType.THEATER_ID, theaterId = "2")
        )
        val cart1 = Cart(theaterId = "1")
        val cart2 = Cart(theaterId = "2")

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(true, predicate(cart1))
        assertEquals(true, predicate(cart2))
    }

     @Test fun multipleMovieRecordsShouldAllowOneMovieOrTheOther() {
        // given
        val rows = listOf(
                RuleRecord(type = RecordType.MOVIE_ID, movieId = "1"),
                RuleRecord(type = RecordType.MOVIE_ID, movieId = "2")
        )
        val cart1 = Cart(movieId = "1")
        val cart2 = Cart(movieId = "2")

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(true, predicate(cart1))
        assertEquals(true, predicate(cart2))
    }

    @Test fun aTheaterRecordShouldCreateATheaterIdPredicate() {
        // given
        val rows = listOf(RuleRecord(type = RecordType.THEATER_ID, theaterId = "1"))
        val cart = Cart(theaterId = "1")

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(true, predicate(cart))
    }

    @Test fun aMovieRecordShouldCreateAMovieIdPredicate() {
        // given
        val rows = listOf(RuleRecord(type = RecordType.MOVIE_ID, movieId = "1"))
        val cart = Cart(movieId = "1")

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(true, predicate(cart))
    }

    @Test fun allMatchingCompositeShouldMatchPredicate() {
        // given
        val rows = listOf(
                RuleRecord(type = RecordType.MOVIE_ID, movieId = "1"),
                RuleRecord(type = RecordType.THEATER_ID, theaterId = "1"),
                RuleRecord(type = RecordType.IS_VIP)
        )
        val cart = Cart(movieId = "1", theaterId = "1", isVip = true)

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(true, predicate(cart))
    }

    @Test fun nonVipShouldNotMatchCompositeRuleContainingVIP() {
        // given
        val rows = listOf(
                RuleRecord(type = RecordType.MOVIE_ID, movieId = "1"),
                RuleRecord(type = RecordType.THEATER_ID, theaterId = "1"),
                RuleRecord(type = RecordType.IS_VIP)
        )
        val cart = Cart(movieId = "1", theaterId = "1", isVip = false)

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(false, predicate(cart))
    }

    @Test fun misMatchedMovieShouldNotMatchMovieRule() {
        // given
        val rows = listOf(
                RuleRecord(type = RecordType.MOVIE_ID, movieId = "1"),
                RuleRecord(type = RecordType.THEATER_ID, theaterId = "1"),
                RuleRecord(type = RecordType.IS_VIP)
        )
        val cart = Cart(movieId = "2", theaterId = "1", isVip = true)

        //when
        val predicate = DefaultPredicateFactory.createCompositePredicate(rows)

        //then
        assertEquals(false, predicate(cart))
    }
}