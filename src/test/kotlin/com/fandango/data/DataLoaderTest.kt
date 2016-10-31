package com.fandango.data

import org.junit.Test
import org.springframework.jdbc.core.JdbcTemplate

class DataLoaderTest {
    @Test fun dataShouldLoadCorrectly() {

        // when
        val jdbcTemplate = JdbcTemplate(buildDb())

        // then
        jdbcTemplate.execute("select * from promos")
        jdbcTemplate.execute("select * from rules")
        jdbcTemplate.execute("select * from campaigns")
    }

}