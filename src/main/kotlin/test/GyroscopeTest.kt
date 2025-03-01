package top.suhasdissa.test

import com.pi4j.context.Context
import top.suhasdissa.hardware.sensors.GyroscopeController

class GyroscopeTest(private val pi4j: Context) {

    fun perform() {
        val gyroscope = GyroscopeController(pi4j)
        while (true) {
            val (x, y, z) = gyroscope.readGyroscope()
            println("Gyro X: $x deg/s, Gyro Y: $y deg/s, Gyro Z: $z deg/s")
            Thread.sleep(500)
        }
    }
}