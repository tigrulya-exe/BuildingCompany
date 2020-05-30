package nsu.manasyan.buildingcompany.buildingobjects.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.buildingobjects.model.Material

@NoArgConstructor
data class MaterialDto(var name: String) : Dto<Material>()