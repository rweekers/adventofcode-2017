package nl.orangeflamingo.adventofcode2017.four

import org.junit.Assert
import org.junit.Test


class Exercise4Test {

    @Test
    fun exercise4SilverTest() {
        val exc4 = Exercise4()
        val answer = exc4.exercise4("/test.txt")
        Assert.assertEquals(2, answer)
    }

}