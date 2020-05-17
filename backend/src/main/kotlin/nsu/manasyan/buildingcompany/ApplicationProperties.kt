package nsu.manasyan.buildingcompany

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties(prefix = "app")
@Component
class ApplicationProperties {
    lateinit var domainName: String

    lateinit var path: String

    var port: Int = 0
}