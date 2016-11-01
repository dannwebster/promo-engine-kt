package com.fandango.data

import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import kotlin.test.assertEquals

class PromoDaoTest() {

    @Test fun daoShouldPromoById() {
        // given
        val db = buildDb()
        val jdbcTemplate = JdbcTemplate(db)
        val subject = PromoDao(jdbcTemplate)

        // when
        val records = subject.getPromoById("9D969E2C-5100-4FCA-AC4C-2623AA7F2BD6")

        // then
        assertEquals(1, records.size)
    }
}
