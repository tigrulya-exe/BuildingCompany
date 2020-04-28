package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.BuildingObject
import org.springframework.data.jpa.repository.JpaRepository

interface BuildingObjectRepository : JpaRepository<BuildingObject, Int>