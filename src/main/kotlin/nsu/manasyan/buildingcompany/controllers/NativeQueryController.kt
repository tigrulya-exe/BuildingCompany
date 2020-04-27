package nsu.manasyan.buildingcompany.controllers

import nsu.manasyan.buildingcompany.services.NativeQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${application-path}")
class NativeQueryController(private val queryService: NativeQueryService) {

    @GetMapping("/query")
    fun getQueryResults(@RequestParam query: String) : MutableList<*>{
        return queryService.getQueryResults(query)
    }
}