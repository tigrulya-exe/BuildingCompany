package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Area
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AreaRepository : JpaRepository<Area, Int>