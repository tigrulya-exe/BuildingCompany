package nsu.manasyan.buildingcompany.abstracts.controllers

import nsu.manasyan.buildingcompany.abstracts.dto.NativeQueryResultsDto
import nsu.manasyan.buildingcompany.abstracts.services.NativeQueryService
import nsu.manasyan.buildingcompany.util.FindRequestParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application.path}")
class NativeQueryController(private val queryService: NativeQueryService) {

    @GetMapping("/query")
    fun getQueryResults(@RequestParam query: String, params: FindRequestParameters?): NativeQueryResultsDto {
        return queryService.getQueryResults(query, params)
    }
}