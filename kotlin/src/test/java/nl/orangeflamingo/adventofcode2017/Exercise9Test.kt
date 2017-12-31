package nl.orangeflamingo.adventofcode2017

import org.junit.Assert
import org.junit.Test


class Exercise9Test {

    @Test
    fun exercise9aSilverTest() {
        val answer = Exercise9("/test/test9-silver-a.txt").silverExercise9()
        Assert.assertEquals(1, answer)
    }

    @Test
    fun exercise9bSilverTest() {
        val answer = Exercise9("/test/test9-silver-b.txt").silverExercise9()
        Assert.assertEquals(6, answer)
    }

    @Test
    fun exercise9cSilverTest() {
        val answer = Exercise9("/test/test9-silver-c.txt").silverExercise9()
        Assert.assertEquals(5, answer)
    }

    @Test
    fun exercise9dSilverTest() {
        val answer = Exercise9("/test/test9-silver-d.txt").silverExercise9()
        Assert.assertEquals(16, answer)
    }

    @Test
    fun exercise9eSilverTest() {
        val answer = Exercise9("/test/test9-silver-e.txt").silverExercise9()
        Assert.assertEquals(1, answer)
    }

    @Test
    fun exercise9fSilverTest() {
        val answer = Exercise9("/test/test9-silver-f.txt").silverExercise9()
        Assert.assertEquals(9, answer)
    }

    @Test
    fun exercise9gSilverTest() {
        val answer = Exercise9("/test/test9-silver-g.txt").silverExercise9()
        Assert.assertEquals(9, answer)
    }

    @Test
    fun exercise9hSilverTest() {
        val answer = Exercise9("/test/test9-silver-h.txt").silverExercise9()
        Assert.assertEquals(3, answer)
    }


    @Test
    fun exercise9aGoldTest() {
        val answer = Exercise9("/test/test9-gold-a.txt").goldExercise9()
        Assert.assertEquals(0, answer)
    }


    @Test
    fun exercise9bGoldTest() {
        val answer = Exercise9("/test/test9-gold-b.txt").goldExercise9()
        Assert.assertEquals(0, answer)
    }
}