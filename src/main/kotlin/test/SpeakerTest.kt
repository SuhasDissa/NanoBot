package top.suhasdissa.test

import SpeakerController
import com.pi4j.context.Context

class SpeakerTest(private val pi4j: Context) {

    // Define blink pattern: even indices are ON times, odd indices are OFF times
    private val pattern = arrayListOf(
        0,
        150,
        150,
        150,
        150,
        75,
        75,
        150,
        150,
        150,
        150,
        450
    )

    fun perform() {
        val speaker = SpeakerController(pi4j)
        speaker.playPattern(pattern)
    }
}