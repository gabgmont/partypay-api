package br.com.fairie.partypay.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
open class SwaggerConfiguration {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(arrayListOf(securityContext()))
                .securitySchemes(arrayListOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fairie.partypay"))
                .paths(PathSelectors.any())
                .build()

    }

    @Value("\${application.name}")
    private lateinit var name: String

    @Value("\${application.version}")
    private lateinit var version: String

    @Value("\${application.description}")
    private lateinit var description: String

    @Bean
    open fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder()
                .title(name)
                .version(version)
                .description(description)
                .build()

    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    private fun defaultAuth(): List<SecurityReference?> {
        val authorizationScopes = arrayOf(AuthorizationScope("global", "accessEverything"))
        return arrayListOf(SecurityReference("JWT", authorizationScopes))
    }
}