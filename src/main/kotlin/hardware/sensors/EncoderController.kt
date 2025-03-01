package top.suhasdissa.hardware.sensors

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalInput
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider
import java.util.concurrent.atomic.AtomicInteger

class EncoderController(
    pi4j: Context,
    pinA: Int, pinB: Int
) {

    private val lastStateA = AtomicInteger(0)
    private val position = AtomicInteger(0)

    // Configure GPIO inputs
    private val rotaryA: DigitalInput = pi4j.din<PiGpioDigitalInputProvider>().create(pinA)
    private val rotaryB: DigitalInput = pi4j.din<PiGpioDigitalInputProvider>().create(pinB)

    init {
        rotaryA.addListener(
            {
                val stateA = rotaryA.state().value()
                val stateB = rotaryB.state().value()

                if (stateA != lastStateA.get()) {
                    if (stateB != stateA) {
                        position.incrementAndGet()
                        println("Rotated CLOCKWISE | Position: ${position.get()}")
                    } else {
                        position.decrementAndGet()
                        println("Rotated COUNTERCLOCKWISE | Position: ${position.get()}")
                    }
                    lastStateA.set(stateA.toInt())
                }
            }
        )
    }

    fun getPosition(): Int = position.get()
}

fun main() {
    val pi4j: Context = Pi4J.newAutoContext()

    // Define pin numbers
    val pinA = 17  // CLK
    val pinB = 27  // DT
    val pinButton = 22  // SW (Optional)

    // Initialize Encoder
    val encoder = EncoderController(pi4j, pinA, pinB)

    // Keep running indefinitely
    println("Rotary Encoder Controller Running... Press Ctrl+C to Exit")
    while (true) {
        Thread.sleep(100)
    }
}