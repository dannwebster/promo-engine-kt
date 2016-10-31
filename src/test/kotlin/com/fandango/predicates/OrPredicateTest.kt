package com.fandango.predicates

import com.fandango.model.Cart
import com.fandango.model.False
import com.fandango.model.True
import com.fandango.model.or
import org.junit.Assert.*
import org.junit.Test

class OrPredicateTest {

    @Test fun allTrueShouldCreateTrue() {
        // given
        val subject = or(listOf(True, True, True))

        // then
        assertEquals(true, subject(Cart()))
    }

    @Test fun mixedTrueAndFalseShouldCreateTrue() {
        // given
        val subject = or(listOf(False, True, False))

        // then
        assertEquals(true, subject(Cart()))
    }

    @Test fun allFalseShouldCreateFalse() {
        // given
        val subject = or(listOf(False, False, False))

        // then
        assertEquals(false, subject(Cart()))
    }
}