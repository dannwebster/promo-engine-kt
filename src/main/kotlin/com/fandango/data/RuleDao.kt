package com.fandango.data

import com.fandango.model.RecordType
import com.fandango.model.RuleRecord
import org.springframework.jdbc.core.JdbcTemplate
import java.math.BigDecimal
import java.sql.ResultSet


class RuleDao(val jdbcTemplate: JdbcTemplate) {
    fun String?.toBigDecimal(): BigDecimal? = if (this.isNullOrEmpty()) null else BigDecimal(this)

    fun recordsByCampaignId(campaignId: String) = jdbcTemplate
            .query("select * from rules where campaign_id = ?", arrayOf(campaignId))
            { rs, rownum -> mapRow(rs) }

    fun mapRow(rs: ResultSet) = RuleRecord(type = RecordType.valueOf(rs.getString("rule_type")),
                                    movieId = rs.getString("movie_id"),
                                    theaterId = rs.getString("theater_id"),
                                    discount = rs.getString("discount").toBigDecimal()
                                )
}