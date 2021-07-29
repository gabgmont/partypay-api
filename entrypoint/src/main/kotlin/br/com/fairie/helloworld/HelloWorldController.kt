package br.com.fairie.helloworld

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello-world")
@Api(
    value = "Hello World",
    tags = ["Hello World"]
)
class HelloWorldController {

    @GetMapping
    @ApiOperation(value = "Hello World!")
    fun helloWorld(): String{
        return "Hello World!"
    }
}