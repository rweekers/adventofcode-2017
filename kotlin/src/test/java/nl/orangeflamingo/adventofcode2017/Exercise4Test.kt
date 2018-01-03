package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise4Test {

    @Test
    fun exercise4SilverTest() {
        val exc4 = Exercise4()
        val answer = exc4.silverExercise4("/test/four/test4.txt")
        Assert.assertEquals(3, answer)
    }

    @Test
    fun exercise4GoldTest() {
        val exc4 = Exercise4()
        val answer = exc4.goldExercise4("/test/four/test4.txt")
        Assert.assertEquals(2, answer)
    }
}