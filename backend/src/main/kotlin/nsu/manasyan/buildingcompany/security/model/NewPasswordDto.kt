package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
data class NewPasswordDto(val newPassword: String)