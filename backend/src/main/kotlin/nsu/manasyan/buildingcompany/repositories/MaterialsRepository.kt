package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Material
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialsRepository : JpaRepository<Material, Int>