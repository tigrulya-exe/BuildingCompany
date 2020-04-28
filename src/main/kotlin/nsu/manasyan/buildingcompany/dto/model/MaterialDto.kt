package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Material

@NoArgConstructor
data class MaterialDto(var name: String) : Dto<Material>()