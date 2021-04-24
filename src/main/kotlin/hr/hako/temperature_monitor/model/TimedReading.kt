package hr.hako.temperature_monitor.model

interface TimedReading {
    val value: Double
    val timeStamp: Long
}