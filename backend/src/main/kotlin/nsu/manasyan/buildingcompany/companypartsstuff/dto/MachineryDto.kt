package nsu.manasyan.buildingcompany.companypartsstuff.dto

import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.companypartsstuff.model.Machinery

@NoArgConstructor
data class MachineryDto(
    var type: String,
    var licencePlateNumber: String,
    var buildingObjectId: Int?
) : Dto<Machinery>()