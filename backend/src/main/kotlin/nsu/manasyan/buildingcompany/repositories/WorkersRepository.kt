package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.workers.Worker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface WorkersRepository : JpaRepository<Worker, Int>, JpaSpecificationExecutor<Worker>