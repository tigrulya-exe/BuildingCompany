package nsu.manasyan.buildingcompany.security.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.security.model.Permission

@NoArgConstructor
class PermissionDto(var name: String) : Dto<Permission>()