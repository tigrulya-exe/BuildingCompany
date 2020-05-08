package nsu.manasyan.buildingcompany

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args);
}

fun <T : Any> T.logger(): Logger = LoggerFactory.getLogger(javaClass)
