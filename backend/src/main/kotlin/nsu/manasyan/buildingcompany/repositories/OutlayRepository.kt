package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.Outlay
import nsu.manasyan.buildingcompany.model.OutlayExceedance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OutlayRepository : JpaRepository<Outlay, Int>

@Repository
interface OutlayExceedanceRepository : JpaRepository<OutlayExceedance, Int>