package hr.hako.temperature_monitor.controller

import hr.hako.temperature_monitor.model.controller_models.CombinedReading
import hr.hako.temperature_monitor.model.entities.TemperatureReading
import hr.hako.temperature_monitor.persistence.interactors.ReadingInteractor
import hr.hako.temperature_monitor.persistence.interactors.ReadingInteractorImpl
import hr.hako.temperature_monitor.persistence.repository.HumidityRepository
import hr.hako.temperature_monitor.persistence.repository.TemperatureRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class TemperatureController(
    val readingInteractor: ReadingInteractor
) {

    @MessageMapping("/combinedReading")
    suspend fun readingMapping(combinedReading: CombinedReading) {
        readingInteractor.writeReading(combinedReading)
        println("readingMapping")
    }

}