package nsu.manasyan.buildingcompany.workers.dto

import nsu.manasyan.buildingcompany.configuration.NoArgConstructor

@NoArgConstructor
class MasonDto(
    name: String,
    surname: String,
    patronymic: String?,
    experienceYears: Int?,
    brigadeId: Int?,
    var bricksPerHour: Int
) : WorkerDto(
    name,
    surname,
    patronymic,
    experienceYears,
    brigadeId
)