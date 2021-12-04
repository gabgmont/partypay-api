package br.com.fairie.partypay.configuration.resource.datasource

import br.com.fairie.partypay.configuration.resource.resource.H2Resource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class H2DataSourceConfiguration {

    @Bean
    open fun loadH2Datasource(
            resource: H2Resource
    ): DataSource {
        return DataSourceBuilder.create()
                .driverClassName(resource.driverClassName)
                .url(resource.url)
                .username(resource.username)
                .password(resource.password)
                .build()
    }
}