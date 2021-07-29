package br.com.fairie.partypay.configuration.helloworld

import br.com.fairie.helloworld.HelloWorldController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldConfiguration {

    @Bean
    fun loadHelloWorld() = HelloWorldController()
}