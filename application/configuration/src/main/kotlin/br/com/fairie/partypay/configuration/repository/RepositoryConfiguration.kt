package br.com.fairie.partypay.configuration.repository

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource

@Configuration
open class RepositoryConfiguration {

    companion object{
        const val H2_JDBC_TEMPLATE = "h2JdbcTemplate"
        const val H2_DATA_SOURCE = "h2Datasource"
    }

    @Bean(H2_DATA_SOURCE)
    @Qualifier(H2_JDBC_TEMPLATE)
    open fun loadJdbcH2Template(
        @Qualifier(H2_DATA_SOURCE) datasource: DataSource
    ): JdbcTemplate {
        return JdbcTemplate(datasource)
    }

}