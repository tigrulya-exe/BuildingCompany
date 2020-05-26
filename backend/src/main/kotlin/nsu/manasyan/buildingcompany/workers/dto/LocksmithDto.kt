package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
class LocksmithDto (
    name: String,
    surname: String,
    patronymic: String?,
    experienceYears: Int?,
    brigadeId: Int?,
    var category: Int,
    var higherEducation: Boolean
) : WorkerDto(
    name,
    surname,
    patronymic,
    experienceYears,
    brigadeId
)