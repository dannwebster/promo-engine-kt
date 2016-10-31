package com.fandango.data

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType


fun buildDb() = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)

            .addScript("db/ddl/campaigns.ddl")
            .addScript("db/ddl/rules.ddl")
            .addScript("db/ddl/promos.ddl")

            .addScript("db/sql/campaigns.sql")
            .addScript("db/sql/rules.sql")
            .addScript("db/sql/promos.sql")
            .build()
