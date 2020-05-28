package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.NoArgConstructor

@NoArgConstructor
data class Credentials(var login: String, var password: String)