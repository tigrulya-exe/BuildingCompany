package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.ConstructionManagement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConstructionManagementsRepository : JpaRepository<ConstructionManagement, Int>