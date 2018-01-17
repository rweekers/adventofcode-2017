package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise13Test {

    private val exc13 = Exercise13("/test/thirteen/test13.txt")

    @Test
    fun exercise13SilverTest() {
        Assert.assertEquals(0, exc13.silverExercise13())
    }


}