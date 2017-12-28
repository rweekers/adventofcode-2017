package nl.orangeflamingo.adventofcode2017.seven

import org.junit.Assert
import org.junit.Test


class Exercise7Test {

    @Test
    fun exercise7SilverTest() {
        val exc7 = Exercise7("/test7.txt")
        val answer = exc7.silverExercise7()
        Assert.assertEquals("tknk", answer)
    }

    @Test
    fun exercise7GoldTest() {
        val exc7 = Exercise7("/test7.txt")
        val answer = exc7.goldExercise7()
        Assert.assertEquals(60, answer.second)
    }
}