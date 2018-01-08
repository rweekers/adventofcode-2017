package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise11Test {

    @Test
    fun exercise11aSilverTest() {
        val answer = Exercise11("/test/eleven/test11-a.txt").silverExercise11()
        Assert.assertEquals(3, answer)
    }

    @Test
    fun exercise11bSilverTest() {
        val answer = Exercise11("/test/eleven/test11-b.txt").silverExercise11()
        Assert.assertEquals(0, answer)
    }

    @Test
    fun exercise11cSilverTest() {
        val answer = Exercise11("/test/eleven/test11-c.txt").silverExercise11()
        Assert.assertEquals(2, answer)
    }

    @Test
    fun exercise11dSilverTest() {
        val answer = Exercise11("/test/eleven/test11-d.txt").silverExercise11()
        Assert.assertEquals(3, answer)
    }

}