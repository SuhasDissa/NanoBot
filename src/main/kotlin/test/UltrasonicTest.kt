package top.suhasdissa.test

import com.pi4j.context.Context
import top.suhasdissa.hardware.sensors.UltrasonicController

class UltrasonicTest(private val pi4j: Context) {
    private val ultrasonic = UltrasonicController(pi4j = pi4j)

    fun perform() {
        try {
            while (true) {
                val distance = ultrasonic.getDistance()
                /*if (distance == -1.0) {
                    break
                }*/
                println("Distance: %.2f cm".format(distance))
                Thread.sleep(500)
            }
        } finally {
            pi4j.shutdown()
        }
    }
}