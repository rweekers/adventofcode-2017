package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise6Test {

    @Test
    fun exercise6SilverTest() {
        val exc6 = Exercise6()
        val answer = exc6.silverExercise6("/test/test6.txt")
        Assert.assertEquals(5, answer)
    }

    @Test
    fun exercise6GoldTest() {
        val exc6 = Exercise6()
        exc6.silverExercise6("/test/test6.txt")
        val answer = exc6.goldExercise6()
        Assert.assertEquals(4, answer)
    }
}