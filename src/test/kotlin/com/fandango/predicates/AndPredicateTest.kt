package com.fandango.predicates

import com.fandango.model.Cart
import com.fandango.model.False
import com.fandango.model.True
import com.fandango.model.and
import org.junit.Assert.*
import org.junit.Test

class AndPredicateTest {

    @Test fun allTrueShouldCreateTrue() {
        // given
        val subject = and(listOf(True, True, True))

        // then
        assertEquals(true, subject(Cart()))
    }

    @Test fun mixedTrueAndFalseShouldCreateFalse() {
        // given
        val subject = and(listOf(False, True, False))

        // then
        assertEquals(false, subject(Cart()))
    }

    @Test fun allFalseShouldCreateFalse() {
        // given
        val subject = and(listOf(False, False, False))

        // then
        assertEquals(false, subject(Cart()))
    }
}