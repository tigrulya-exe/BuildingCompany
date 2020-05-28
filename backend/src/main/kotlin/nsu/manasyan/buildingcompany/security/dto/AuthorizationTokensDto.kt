package nsu.manasyan.buildingcompany.security.dto

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
data class AuthorizationTokensDto(
    var jwt: String,
    var refreshToken: String
)