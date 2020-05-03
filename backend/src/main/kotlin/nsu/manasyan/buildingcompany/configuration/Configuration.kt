package nsu.manasyan.buildingcompany.configuration

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration.AccessLevel
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


annotation class NoArgConstructor

@Configuration
class GlobalConfiguration {
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

//    @Bean
//    fun logFilter(): CommonsRequestLoggingFilter? {
//        val filter = CommonsRequestLoggingFilter()
//        filter.setIncludeQueryString(true)
//        filter.setIncludePayload(true)
//        filter.setMaxPayloadLength(10000)
//        filter.setIncludeHeaders(false)
//        filter.setAfterMessagePrefix("REQUEST DATA : ")
//        return filter
//    }

    @Configuration
    class MyConfiguration {
        @Bean
        fun corsConfigurer(): WebMvcConfigurer {
            return object : WebMvcConfigurerAdapter() {
                override fun addCorsMappings(registry: CorsRegistry) {
                    registry.addMapping("/**")
                }
            }
        }
    }
}