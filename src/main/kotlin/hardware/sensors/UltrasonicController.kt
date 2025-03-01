package top.suhasdissa.hardware.sensors

import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalInput
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider
import top.suhasdissa.config.GPIO
import kotlin.system.measureNanoTime

class UltrasonicController(
    pi4j: Context) {

    private val trigger: DigitalOutput = pi4j.dout<PiGpioDigitalOutputProvider>().create(GPIO.ULTRASONIC_TRIGGER_PIN)
    private val echo: DigitalInput = pi4j.din<PiGpioDigitalInputProvider>().create(GPIO.ULTRASONIC_ECHO_PIN)

    fun getDistance(): Double {
        trigger.high()
        Thread.sleep(0, 10000) // 10µs pulse
        trigger.low()

        // Wait for echo to go HIGH (timeout after 60ms)
        val startTime = System.nanoTime()
        while (echo.state() == DigitalState.LOW) {
            if ((System.nanoTime() - startTime) > 60_000_000) return -1.0
        }

        // Measure pulse duration
        val duration = measureNanoTime {
            val pulseStart = System.nanoTime()
            while (echo.state() == DigitalState.HIGH) {
                if ((System.nanoTime() - pulseStart) > 60_000_000) return -1.0
            }
        }

        return (duration / 1_000.0) * 0.0343 / 2
    }
}