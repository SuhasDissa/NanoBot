package top.suhasdissa

import com.pi4j.Pi4J
import com.pi4j.context.Context
import top.suhasdissa.test.MotorControlTest
import top.suhasdissa.test.SpeakerTest


fun main() {
    val pi4j: Context = Pi4J.newAutoContext()
    /*val sp = SpeakerController(pi4j)
    sp.play()*/

    SpeakerTest(pi4j).perform()
    MotorControlTest(pi4j).perform()
}