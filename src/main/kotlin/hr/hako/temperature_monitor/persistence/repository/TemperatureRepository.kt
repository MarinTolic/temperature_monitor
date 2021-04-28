package hr.hako.temperature_monitor.persistence.repository

import hr.hako.temperature_monitor.model.entities.TemperatureReading
import org.springframework.data.jpa.repository.JpaRepository

abstract class TemperatureRepository : JpaRepository<TemperatureReading, Long> {
}