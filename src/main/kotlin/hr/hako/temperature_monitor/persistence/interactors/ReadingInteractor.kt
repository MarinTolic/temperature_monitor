package hr.hako.temperature_monitor.persistence.interactors

import hr.hako.temperature_monitor.model.controller_models.CombinedReading

interface ReadingInteractor {
    fun tryEmitReading(combinedReading: CombinedReading)
}