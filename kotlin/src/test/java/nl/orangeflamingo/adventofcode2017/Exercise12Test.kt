package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise12Test {

    private val exc12 = Exercise12("/test/twelve/test12.txt")

    @Test
    fun exercise12SilverTest() {
        Assert.assertEquals(6, exc12.silverExercise12())
    }

    @Test
    fun exercise12GoldTest() {
        Assert.assertEquals(2, exc12.goldExercise12())
    }

}