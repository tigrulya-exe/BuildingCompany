package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.model.Post

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