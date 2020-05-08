package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Post
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import nsu.manasyan.buildingcompany.util.filters.Filter

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
