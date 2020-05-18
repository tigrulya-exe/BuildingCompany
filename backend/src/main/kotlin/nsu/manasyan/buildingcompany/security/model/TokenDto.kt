package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
data class TokenDto(var token: String)