package com.fandango

import com.fandango.data.RuleDao
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.jdbc.core.JdbcTemplate
import com.fandango.data.buildDb
import com.fandango.factories.ActionFactory
import com.fandango.factories.DefaultPredicateFactory
import com.fandango.factories.PromoFactory
import com.fandango.model.PromoEngine
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class PromoEngineApplication() : CommandLineRunner {
    @Bean open fun dataSource() = buildDb()
    @Bean open fun jdbcTemplate() = JdbcTemplate(dataSource())
    @Bean open fun ruleDao() = RuleDao(jdbcTemplate())
    @Bean open fun actionFactory() = ActionFactory()
    @Bean open fun predicateFactory() = DefaultPredicateFactory
    @Bean open fun promoFactory() = PromoFactory(predicateFactory(), actionFactory())
    @Bean open fun promoEngine() = PromoEngine(ruleDao(), promoFactory())

    override fun run(vararg args: String?) {
    }
}

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(PromoEngineApplication::class.java, *args)
}