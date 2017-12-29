package nl.orangeflamingo.adventofcode2017.eight

import org.junit.Assert
import org.junit.Test


class Exercise8Test {

    @Test
    fun exercise8SilverTest() {
        val exc8 = Exercise8("/test8.txt")
        val answer = exc8.silverExercise8()
        Assert.assertEquals(1, answer)
    }

    @Test
    fun exercise8GoldTest() {
        val exc8 = Exercise8("/test8.txt")
        val answer = exc8.goldExercise8()
        Assert.assertEquals(10, answer)
    }
}