import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider
import top.suhasdissa.config.GPIO
import kotlin.concurrent.thread

class SpeakerController(pi4j: Context) {
    private val speaker: DigitalOutput = pi4j.dout<PiGpioDigitalOutputProvider>().create(GPIO.SPEAKER_PIN)

    fun playPattern(pattern:ArrayList<Int>) {
        thread() {
            for (i in pattern.indices step 2) {
                speaker.high()
                Thread.sleep(pattern[i].toLong()) // ON time

                if (i + 1 < pattern.size) {
                    speaker.low()
                    Thread.sleep(pattern[i + 1].toLong()) // OFF time
                }
            }
            speaker.low()
        }
    }
}
