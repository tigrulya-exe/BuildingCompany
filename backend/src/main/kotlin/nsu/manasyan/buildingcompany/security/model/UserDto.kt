package nsu.manasyan.buildingcompany.security.model

import nsu.manasyan.buildingcompany.dto.model.Dto

data class UserDto(
    var nickname: String,
    var email: String,
    var fullName: String,
    var password: String
) : Dto<User>()