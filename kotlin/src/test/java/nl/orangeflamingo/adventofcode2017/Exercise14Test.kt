package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise14Test {

    private val exc14 = Exercise14("/test/fourteen/test14.txt")

    @Test
    fun exercise14SilverTest() {
        assertEquals(8108, exc14.silverExercise14())
    }

    @Test
    fun exercise14GoldTest() {
        assertEquals(1242, exc14.goldExercise14())
    }
}