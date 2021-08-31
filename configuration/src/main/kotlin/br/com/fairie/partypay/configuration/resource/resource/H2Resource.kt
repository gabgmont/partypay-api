package br.com.fairie.partypay.configuration.resource.resource

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class H2Resource {
    @Value("\${spring.datasource.driverClassName}")
    lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Value("\${spring.datasource.username}")
    lateinit var username: String

    @Value("\${spring.datasource.password}")
    lateinit var password: String
}