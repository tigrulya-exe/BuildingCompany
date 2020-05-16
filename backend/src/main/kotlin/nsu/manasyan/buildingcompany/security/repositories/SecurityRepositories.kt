package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.repositories.JpaFilterRepository
import nsu.manasyan.buildingcompany.security.model.User

interface UserRepository : JpaFilterRepository<User, Int>
