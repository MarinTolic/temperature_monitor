package hr.hako.temperature_monitor.persistence.interactors

import hr.hako.temperature_monitor.model.controller_models.CombinedReading
import hr.hako.temperature_monitor.model.entities.TemperatureReading

interface ReadingInteractor {
    suspend fun getAllTemperatureReadings(): List<TemperatureReading>
    suspend fun writeReading(combinedReading: CombinedReading)

    fun loadDummies()
}