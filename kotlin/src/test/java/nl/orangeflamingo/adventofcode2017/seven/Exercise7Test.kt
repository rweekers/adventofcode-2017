package nl.orangeflamingo.adventofcode2017.seven

import org.junit.Assert
import org.junit.Test


class Exercise7Test {

    @Test
    fun exercise7SilverTest() {
        val exc7 = Exercise7()
        val answer = exc7.silverExercise7("/test7.txt")
        Assert.assertEquals("tknk", answer)
    }
}