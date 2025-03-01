package hardware.servo

import com.pi4j.context.Context
import com.pi4j.io.pwm.Pwm
import com.pi4j.io.pwm.PwmType

class ServoController(
    pi4j: Context,
    pin: Int,
    frequency: Int = 50,
    private val minAngle: Float = -90f,
    private val maxAngle: Float = 90f,
    private val minPulseWidth: Float = 2f,
    private val maxPulseWidth: Float = 12f
) {
    private val pwm: Pwm = pi4j.create(
        Pwm.newConfigBuilder(pi4j)
            .id("servo_pwm")
            .address(pin)
            .pwmType(PwmType.SOFTWARE)
            .frequency(frequency)
            .build()
    )

    private var rangeMin = minAngle
    private var rangeMax = maxAngle

    fun setPercent(percent: Int) {
        val angle = minAngle + (maxAngle - minAngle) * (percent / 100.0f)
        setAngle(angle)
    }

    fun setAngle(angle: Float) {
        val pulseWidth = minPulseWidth + (maxPulseWidth - minPulseWidth) * ((angle - minAngle) / (maxAngle - minAngle))
        pwm.on(pulseWidth.toInt())
    }

    fun setRange(min: Float, max: Float) {
        rangeMin = min
        rangeMax = max
    }

    fun moveOnRange(value: Float) {
        val mappedAngle = minAngle + (maxAngle - minAngle) * ((value - rangeMin) / (rangeMax - rangeMin))
        setAngle(mappedAngle)
    }

    fun reset() {
        pwm.off()
    }

}

