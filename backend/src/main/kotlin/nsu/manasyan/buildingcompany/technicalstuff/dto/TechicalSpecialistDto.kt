package nsu.manasyan.buildingcompany.technicalstuff.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.abstracts.model.Post
import nsu.manasyan.buildingcompany.technicalstuff.model.TechnicalSpecialist

@NoArgConstructor
data class TechnicalSpecialistDto(
    var name: String,
    var surname: String,
    var patronymic: String?,
    var educationalInstitution: String,
    var experienceYears: Int?,
    var areaId: Int?,
    var post: Post?,
    var knowledgeOfEnglish: Boolean?,
    var category: Int?
) : Dto<TechnicalSpecialist>()
