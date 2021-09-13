package br.com.fairie.partypay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.fairie.partypay.configuration"])
class PartypayApplication

fun main(args: Array<String>) {
	runApplication<PartypayApplication>(*args)
}
// $2a$10$LOl4Ggr6c8D7Z3pC951kHeN/fyoBo24mt9hY1RG5w7eiXpTX1YL7a