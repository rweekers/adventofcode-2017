package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise10Test {

    @Test
    fun exercise10SilverTest() {
        val answer = Exercise10("/test/test10.txt").silverExercise10()
        Assert.assertEquals(0, answer)
    }
}