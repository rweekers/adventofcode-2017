package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise20Test {

    @Test
    fun `test input silver`() {
        val exc20 = Exercise20("/test/twenty/test20.txt")
        assertEquals(0, exc20.silverExercise20())
    }

    @Test
    fun `actual input silver`() {
        val exc20 = Exercise20("/input/input20.txt")
        assertEquals(157, exc20.silverExercise20())
    }

    @Test
    fun `test input gold`() {
        val exc20 = Exercise20("/test/twenty/test20.txt")
        assertEquals(2, exc20.goldExercise20())
    }

    @Test
    fun `actual input gold`() {
        val exc20 = Exercise20("/input/input20.txt")
        assertEquals(499, exc20.goldExercise20())
    }
}