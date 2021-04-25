package hr.hako.temperature_monitor.persistence.repository

import hr.hako.temperature_monitor.model.entities.HumidityReading
import org.springframework.data.jpa.repository.JpaRepository

abstract class HumidityRepository : JpaRepository<HumidityReading, Long> {
}