package hr.hako.temperature_monitor.controller

import hr.hako.temperature_monitor.model.entities.TemperatureReading
import hr.hako.temperature_monitor.persistence.interactors.ReadingInteractor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureRestController(
    val readingInteractor: ReadingInteractor
) {

    @GetMapping("/getAll")
    suspend fun getAll(): List<TemperatureReading> =
        readingInteractor.getAllTemperatureReadings()


    @GetMapping("/loadDummies")
    fun loadDummies(){
        readingInteractor.loadDummies()
    }
}