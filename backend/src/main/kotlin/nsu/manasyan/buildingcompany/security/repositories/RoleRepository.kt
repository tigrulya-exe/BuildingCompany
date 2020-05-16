package nsu.manasyan.buildingcompany.security.repositories

import nsu.manasyan.buildingcompany.security.model.Permission
import nsu.manasyan.buildingcompany.security.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<UserRole, Int>

interface PermissionRepository : JpaRepository<Permission, Int>
