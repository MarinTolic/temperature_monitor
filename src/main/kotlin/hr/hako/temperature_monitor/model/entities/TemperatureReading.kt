package hr.hako.temperature_monitor.model.entities

import hr.hako.temperature_monitor.model.TimedReading
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TemperatureReading(
    override val value: Double,
    @Id
    override val timeStamp: Long
) : TimedReading