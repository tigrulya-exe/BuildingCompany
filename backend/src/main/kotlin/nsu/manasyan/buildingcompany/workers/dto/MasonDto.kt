package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.model.Post

@NoArgConstructor
class MasonDto(
    name: String,
    surname: String,
    patronymic: String?,
    experienceYears: Int?,
    brigadeId: Int?,
    post: Post?,
    var bricksPerHour: Int
) : WorkerDto(
    name,
    surname,
    patronymic,
    experienceYears,
    brigadeId,
    post
)