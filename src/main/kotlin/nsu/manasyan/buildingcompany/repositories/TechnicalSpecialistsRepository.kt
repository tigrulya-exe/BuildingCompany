package nsu.manasyan.buildingcompany.repositories

import nsu.manasyan.buildingcompany.model.workers.Foreman
import nsu.manasyan.buildingcompany.model.workers.Master
import nsu.manasyan.buildingcompany.model.workers.TechnicalSpecialist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TechnicalSpecialistsRepository : JpaRepository<TechnicalSpecialist, Int>

@Repository
interface ForemenRepository : JpaRepository<Foreman, Int>

@Repository
interface MastersRepository : JpaRepository<Master, Int>