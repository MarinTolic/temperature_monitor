package hr.hako.temperature_monitor.model.controller_models

import hr.hako.temperature_monitor.model.entities.HumidityReading
import hr.hako.temperature_monitor.model.entities.TemperatureReading

data class CombinedReading(
    val temperatureValue: Double,
    val humidityValue: Double,
    val timestamp: Long
) {
    fun toTemperatureReading(): TemperatureReading =
        TemperatureReading(
            value = temperatureValue,
            timeStamp = timestamp
        )

    fun toHumidityReading(): HumidityReading =
        HumidityReading(
            value = humidityValue,
            timeStamp = timestamp
        )
}
