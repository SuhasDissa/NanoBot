package top.suhasdissa.config

object GPIO {
    const val SR_DATA = 17 // pin 11
    const val SR_LATCH = 27 // pin 13
    const val SR_CLOCK = 22 // pin 15

    val MOTOR_PWM_PINS = intArrayOf(16, 26, 20, 21) // pins 36, 37, 38, 40

    const val SERVO_1_PIN = 10 // pin 19
    const val SERVO_2_PIN = 9 // pin 21

    const val ULTRASONIC_TRIGGER_PIN = 23 // pin 16
    const val ULTRASONIC_ECHO_PIN = 24 // pin 18

    const val SPEAKER_PIN = 25 // pin 22
}