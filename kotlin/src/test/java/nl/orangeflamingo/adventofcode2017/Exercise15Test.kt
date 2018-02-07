package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise15Test {

    private val exc15 = Exercise15(65, 8921)

    @Test
    fun exercise15SilverTest() {
        assertEquals(588, exc15.silverExercise15())
    }

    @Test
    fun exercise15GoldTest() {
        assertEquals(309, exc15.goldExercise15())
    }
}