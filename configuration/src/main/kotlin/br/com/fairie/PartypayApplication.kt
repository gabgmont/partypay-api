package br.com.fairie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.fairie.configuration"])
class PartypayApplication

fun main(args: Array<String>) {
	runApplication<PartypayApplication>(*args)
}
