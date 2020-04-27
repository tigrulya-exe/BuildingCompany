package nsu.manasyan.buildingcompany.configuration

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration.AccessLevel
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {
    @Bean
    fun modelMapper(): ModelMapper {
        val mapper = ModelMapper()
        with(mapper.configuration) {
            matchingStrategy = MatchingStrategies.STRICT
            isFieldMatchingEnabled = true
            isSkipNullEnabled = true
            fieldAccessLevel = AccessLevel.PRIVATE
        }

        return mapper
    }
}