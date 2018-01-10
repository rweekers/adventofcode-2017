package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise12Test {

    @Test
    fun exercise12SilverTest() {
        val answer = Exercise12("/test/twelve/test12.txt").silverExercise12()
        Assert.assertEquals(6, answer)
    }

}