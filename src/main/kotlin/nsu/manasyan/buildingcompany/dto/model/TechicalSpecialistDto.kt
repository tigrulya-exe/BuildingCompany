package nsu.manasyan.buildingcompany.dto.model

import nsu.manasyan.buildingcompany.model.Post
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist

data class TechnicalSpecialistDto(
    var name: String = "",
    var surname: String = "",
    var patronymic: String? = null,
    var educationalInstitution: String = "",
    var experienceYears: Int? = null,
    var post: Post? = null,
    var knowledgeOfEnglish: Boolean? = null,
    var category: Int? = null
) : Dto<TechnicalSpecialist>()
