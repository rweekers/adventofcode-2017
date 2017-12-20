package nl.orangeflamingo.adventofcode2017.five

import org.junit.Assert
import org.junit.Test


class Exercise5Test {

    @Test
    fun exercise4SilverTest() {
        val exc5 = Exercise5()
        val answer = exc5.silverExercise5()
        Assert.assertEquals(0, answer)
    }
}