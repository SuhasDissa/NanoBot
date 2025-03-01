package top.suhasdissa.hardware.sensors

import com.pi4j.context.Context
import com.pi4j.io.i2c.I2C
import com.pi4j.io.i2c.I2CProvider
import com.pi4j.plugin.pigpio.provider.i2c.PiGpioI2CProvider

class GyroscopeController(pi4j: Context) {
    private val i2cProvider: I2CProvider = pi4j.provider(PiGpioI2CProvider::class.java)
    private val i2c: I2C = i2cProvider.create(1, MPU_ADDR)

    companion object {
        private const val MPU_ADDR = 0x68
        private const val GYRO_CONFIG = 0x1B
        private const val PWR_MGMT_1 = 0x6B
        private const val GYRO_XOUT_H = 0x43
        private const val SENSITIVITY = 32.8 // ±1000°/s -> 32.8 LSB/°/s
    }

    init {
        initializeSensor()
    }

    private fun initializeSensor() {
        i2c.writeRegister(PWR_MGMT_1, 0x00.toByte()) // Wake up MPU6050
        i2c.writeRegister(GYRO_CONFIG, 0x10.toByte()) // Set gyroscope range to ±1000°/s
    }

    fun readGyroscope(): Triple<Double, Double, Double> {
        val buffer = ByteArray(6)
        i2c.readRegister(GYRO_XOUT_H, buffer, 0, 6)

        val gyroX = ((buffer[0].toInt() shl 8) or (buffer[1].toInt() and 0xFF)).toShort() / SENSITIVITY
        val gyroY = ((buffer[2].toInt() shl 8) or (buffer[3].toInt() and 0xFF)).toShort() / SENSITIVITY
        val gyroZ = ((buffer[4].toInt() shl 8) or (buffer[5].toInt() and 0xFF)).toShort() / SENSITIVITY

        return Triple(gyroX, gyroY, gyroZ)
    }
}