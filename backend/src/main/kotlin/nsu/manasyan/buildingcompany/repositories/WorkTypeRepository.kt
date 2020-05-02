package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.WorkType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkTypeRepository : JpaRepository<WorkType, Int>