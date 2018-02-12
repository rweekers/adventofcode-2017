package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise17Test {

    private val testpuzzleInput = 3
    private val actualPuzzleInput = 314

    @Test
    fun `test input silver`() {
        val exc17 = Exercise17(testpuzzleInput)
        assertEquals(638, exc17.silverExercise17())
    }

    @Test
    fun `actual input silver`() {
        val exc17 = Exercise17(actualPuzzleInput)
        assertEquals(355, exc17.silverExercise17())
    }

    @Test
    fun `actual input gold`() {
        val exc17 = Exercise17(actualPuzzleInput)
        assertEquals(6154117, exc17.goldExercise17())
    }
}