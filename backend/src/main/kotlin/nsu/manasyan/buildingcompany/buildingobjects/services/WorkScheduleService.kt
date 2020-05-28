package nsu.manasyan.buildingcompany.buildingobjects.services

import nsu.manasyan.buildingcompany.abstracts.services.AbstractCrudService
import nsu.manasyan.buildingcompany.buildingobjects.model.WorkSchedule
import nsu.manasyan.buildingcompany.buildingobjects.repositories.WorkScheduleRepository
import org.springframework.stereotype.Service

@Service
class WorkScheduleService(scheduleRepository: WorkScheduleRepository) :
    AbstractCrudService<WorkSchedule>(scheduleRepository)