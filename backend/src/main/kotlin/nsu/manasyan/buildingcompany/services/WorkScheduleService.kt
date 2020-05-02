package nsu.manasyan.buildingcompany.services

import nsu.manasyan.buildingcompany.model.WorkSchedule
import nsu.manasyan.buildingcompany.repositories.WorkScheduleRepository
import org.springframework.stereotype.Service

@Service
class WorkScheduleService(scheduleRepository: WorkScheduleRepository) : AbstractCrudService<WorkSchedule>(scheduleRepository)