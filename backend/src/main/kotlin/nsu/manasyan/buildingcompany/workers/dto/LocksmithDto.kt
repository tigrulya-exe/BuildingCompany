package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor
import nsu.manasyan.buildingcompany.model.Post

@NoArgConstructor
class LocksmithDto(
    name: String,
    surname: String,
    patronymic: String?,
    experienceYears: Int?,
    brigadeId: Int?,
    post: Post?,
    var category: Int,
    var higherEducation: Boolean
) : WorkerDto(
    name,
    surname,
    patronymic,
    experienceYears,
    brigadeId,
    post
)