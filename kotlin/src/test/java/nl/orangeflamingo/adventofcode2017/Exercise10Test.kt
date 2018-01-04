package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise10Test {

    @Test
    fun exercise10SilverTest() {
        val answer = Exercise10("/test/ten/test10.txt", 5).silverExercise10()
        Assert.assertEquals(12, answer)
    }
}