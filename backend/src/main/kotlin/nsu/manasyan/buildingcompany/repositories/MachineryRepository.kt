package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.ConstructionMachinery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MachineryRepository : JpaRepository<ConstructionMachinery, Int>,
    JpaSpecificationExecutor<ConstructionMachinery>