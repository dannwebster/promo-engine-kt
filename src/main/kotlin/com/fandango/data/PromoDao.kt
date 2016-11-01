package com.fandango.data

import org.springframework.jdbc.core.JdbcTemplate
import java.sql.ResultSet

class PromoDao(val jdbcTemplate: JdbcTemplate) {
    val QUERY_JOIN = """
            SELECT p.promo_guid, p.promo_id, c.campaign_id, c.name
            FROM promos AS p
            INNER JOIN campaigns AS c
            ON c.campaign_id = p.campaign_id
            WHERE p.promo_guid = ?
"""

    fun getPromoById(promoGuid: String) = jdbcTemplate.query(QUERY_JOIN, arrayOf(promoGuid))
        { rs, rownum -> mapPromo(rs) }

    fun mapPromo(rs: ResultSet) = mapOf(
            "campaignId" to rs.getString("campaign_id"),
            "campaignName" to rs.getString("name"),
            "promoId" to rs.getString("promo_id"),
            "promoGuid" to rs.getString("promo_guid")
    )
}