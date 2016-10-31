package com.fandango.data

import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate
import kotlin.test.assertEquals

class RuleDaoTest() {

    @Test fun daoShouldLoadRules() {
        // given
        val db = buildDb()
        val jdbcTemplate = JdbcTemplate(db)
        val subject = RuleDao(jdbcTemplate)

        // when
        val records = subject.recordsByCampaignId("1")

        // then
        assertEquals(4, records.size)
    }
}
