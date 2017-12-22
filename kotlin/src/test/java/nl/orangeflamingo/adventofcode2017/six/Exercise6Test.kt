package nl.orangeflamingo.adventofcode2017.six

import org.junit.Assert
import org.junit.Test


class Exercise6Test {

    @Test
    fun exercise6SilverTest() {
        val exc6 = Exercise6()
        val answer = exc6.silverExercise5("/test6.txt")
        Assert.assertEquals(0, answer)
    }
}