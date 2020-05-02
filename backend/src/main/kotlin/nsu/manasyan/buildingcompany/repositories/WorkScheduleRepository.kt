package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.ScheduleDelay
import nsu.manasyan.buildingcompany.model.WorkSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkScheduleRepository : JpaRepository<WorkSchedule, Int>

@Repository
interface ScheduleDelayRepository : JpaRepository<ScheduleDelay, Int>