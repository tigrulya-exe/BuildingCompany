package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
data class Credentials(var login: String, var password: String)