package br.com.fairie.configuration.helloworld

import br.com.fairie.endpoints.helloworld.HelloWorldController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldConfiguration {

    @Bean
    fun loadHelloWorld() = HelloWorldController()
}