package hr.hako.temperature_monitor.persistence.interactors

import hr.hako.temperature_monitor.model.controller_models.CombinedReading
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

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val mutableJobList = mutableListOf<Job>()
    private val writeFlow = createWriteFlow()

    init {
        collectWriteFlow()
    }

    override fun tryEmitReading(combinedReading: CombinedReading) {
        writeFlow.tryEmit(combinedReading)
    }

    private fun collectWriteFlow() {
        val job = coroutineScope.launch {
            writeFlow.collect { combinedReading ->
                writeReading(combinedReading)
            }
        }
        mutableJobList.add(job)
    }

    private fun writeReading(combinedReading: CombinedReading) {
        temperatureRepository.save(combinedReading.toTemperatureReading())
        humidityRepository.save(combinedReading.toHumidityReading())
    }

    private fun createWriteFlow(): MutableSharedFlow<CombinedReading> =
        MutableSharedFlow(
            extraBufferCapacity = 100,
            onBufferOverflow = BufferOverflow.SUSPEND
        )

    fun closeScope() {
        coroutineScope.cancel()
    }

    fun cancelJobs() {
        mutableJobList.forEach { job ->
            job.cancel()
        }
    }

}