package nsu.manasyan.buildingcompany.security.dto

import nsu.manasyan.buildingcompany.NoArgConstructor
import nsu.manasyan.buildingcompany.abstracts.dto.Dto
import nsu.manasyan.buildingcompany.security.model.User

@NoArgConstructor
data class UserDto(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Dto<User>()