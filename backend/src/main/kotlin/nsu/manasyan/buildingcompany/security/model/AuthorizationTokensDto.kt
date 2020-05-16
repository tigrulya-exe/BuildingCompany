package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
data class AuthorizationTokensDto(
    var jwt: String,
    var refreshToken: String
)