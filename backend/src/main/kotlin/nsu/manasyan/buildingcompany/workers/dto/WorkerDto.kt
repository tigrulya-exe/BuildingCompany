package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.dto.model.Dto
import nsu.manasyan.buildingcompany.model.Post
import nsu.manasyan.buildingcompany.workers.model.Worker

@NoArgConstructor
open class WorkerDto(
    var name: String,
    var surname: String,
    var patronymic: String?,
    var experienceYears: Int?,
    var brigadeId: Int?,
    var post: Post?
) : Dto<Worker>()