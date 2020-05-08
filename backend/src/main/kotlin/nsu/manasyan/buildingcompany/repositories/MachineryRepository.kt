package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Machinery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MachineryRepository : JpaRepository<Machinery, Int>, JpaSpecificationExecutor<Machinery> {
    fun findAllByBuildingObjectAreaManagementId(managementId: Int, pageable: Pageable): Page<Machinery>
}