package hr.hako.temperature_monitor.persistence.interactors

import hr.hako.temperature_monitor.model.controller_models.CombinedReading
import hr.hako.temperature_monitor.model.entities.TemperatureReading
import hr.hako.temperature_monitor.persistence.repository.HumidityRepository
import hr.hako.temperature_monitor.persistence.repository.TemperatureRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import org.springframework.stereotype.Component

@Component
class ReadingInteractorImpl(
    private val temperatureRepository: TemperatureRepository,
    private val humidityRepository: HumidityRepository
) : ReadingInteractor {

    override suspend fun getAllTemperatureReadings(): List<TemperatureReading> {
        return withContext(Dispatchers.IO) {
            temperatureRepository.findAll()
        }
    }

    override suspend fun writeReading(combinedReading: CombinedReading) {
        withContext(Dispatchers.IO) {
            temperatureRepository.save(combinedReading.toTemperatureReading())
            humidityRepository.save(combinedReading.toHumidityReading())
        }
    }

    override fun loadDummies() {
        for (i in 0..10) {
            temperatureRepository.save(
                TemperatureReading(
                    i.toDouble(),
                    i.toLong()
                )
            )
        }
    }

}