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
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
open class SwaggerConfiguration {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.fairie.partypay"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(
                arrayListOf(ParameterBuilder()
                        .name("Authorization")
                        .description("Token Authentication Header")
                        .modelRef(ModelRef("string"))
                        .scalarExample("Bearer ")
                        .parameterType("header")
                        .required(false)
                        .build()))
            .apiInfo(apiInfo())
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
}