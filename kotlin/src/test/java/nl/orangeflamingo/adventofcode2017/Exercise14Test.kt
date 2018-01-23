package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test

class Exercise14Test {

    private val exc14 = Exercise14("/test/fourteen/test14.txt")

    @Test
    fun exercise14SilverTest() {
        Assert.assertEquals(0, exc14.silverExercise14())
    }
}