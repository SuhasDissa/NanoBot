package top.suhasdissa.test

import com.pi4j.context.Context
import hardware.servo.ServoController
import top.suhasdissa.config.GPIO
import java.time.Duration

class ServoTest(private val pi4j: Context) {

    fun perform() {
        val servoMotor = ServoController(pi4j, GPIO.SERVO_1_PIN)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor.setPercent(0)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor.setPercent(100)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor.setPercent(50)

        servoMotor.reset()

        val servoMotor2 = ServoController(pi4j, GPIO.SERVO_2_PIN)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor2.setPercent(0)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor2.setPercent(100)

        Thread.sleep(Duration.ofSeconds(2).toMillis())
        servoMotor2.setPercent(50)

        servoMotor2.reset()
    }
}