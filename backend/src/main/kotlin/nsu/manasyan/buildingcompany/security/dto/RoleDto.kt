package nsu.manasyan.buildingcompany.security.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.security.model.UserRole

@NoArgConstructor
class RoleDto(var role: String) : Dto<UserRole>()