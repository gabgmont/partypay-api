package br.com.fairie.partypay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.fairie.partypay.configuration"])
class PartypayApplication

fun main(args: Array<String>) {
	runApplication<PartypayApplication>(*args)
}
