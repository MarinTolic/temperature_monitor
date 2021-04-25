package hr.hako.temperature_monitor.controller

import hr.hako.temperature_monitor.model.controller_models.CombinedReading
import hr.hako.temperature_monitor.persistence.interactors.ReadingInteractor
import hr.hako.temperature_monitor.persistence.repository.HumidityRepository
import hr.hako.temperature_monitor.persistence.repository.TemperatureRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class TemperatureController(private val readingInteractor: ReadingInteractor) {

    @MessageMapping("/combinedReading")
    fun readingMapping(combinedReading: CombinedReading){
        readingInteractor.tryEmitReading(combinedReading)
    }

}