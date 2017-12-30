package nl.orangeflamingo.adventofcode2017.nine

import org.junit.Assert
import org.junit.Test


class Exercise9Test {

    @Test
    fun exercise9aSilverTest() {
        val answer = Exercise9("/test9-a.txt").silverExercise9()
        Assert.assertEquals(1, answer)
    }

    @Test
    fun exercise9bSilverTest() {
        val answer = Exercise9("/test9-b.txt").silverExercise9()
        Assert.assertEquals(6, answer)
    }

    @Test
    fun exercise9cSilverTest() {
        val answer = Exercise9("/test9-c.txt").silverExercise9()
        Assert.assertEquals(5, answer)
    }

    @Test
    fun exercise9dSilverTest() {
        val answer = Exercise9("/test9-d.txt").silverExercise9()
        Assert.assertEquals(16, answer)
    }

    @Test
    fun exercise9eSilverTest() {
        val answer = Exercise9("/test9-e.txt").silverExercise9()
        Assert.assertEquals(1, answer)
    }

    @Test
    fun exercise9fSilverTest() {
        val answer = Exercise9("/test9-f.txt").silverExercise9()
        Assert.assertEquals(9, answer)
    }

    @Test
    fun exercise9gSilverTest() {
        val answer = Exercise9("/test9-g.txt").silverExercise9()
        Assert.assertEquals(9, answer)
    }

    @Test
    fun exercise9hSilverTest() {
        val answer = Exercise9("/test9-h.txt").silverExercise9()
        Assert.assertEquals(3, answer)
    }
}