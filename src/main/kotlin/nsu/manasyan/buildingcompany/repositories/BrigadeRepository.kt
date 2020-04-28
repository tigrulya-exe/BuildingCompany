package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.workers.Brigade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface BrigadeRepository: JpaRepository<Brigade, Int>, JpaSpecificationExecutor<Brigade>