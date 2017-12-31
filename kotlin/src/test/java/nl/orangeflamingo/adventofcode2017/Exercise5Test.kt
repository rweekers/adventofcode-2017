package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise5Test {

    @Test
    fun exercise5SilverTest() {
        val exc5 = Exercise5()
        val answer = exc5.silverExercise5("/test/test5.txt")
        Assert.assertEquals(5, answer)
    }

    @Test
    fun exercise5GoldTest() {
        val exc5 = Exercise5()
        val answer = exc5.goldExercise5("/test/test5.txt")
        Assert.assertEquals(10, answer)
    }
}