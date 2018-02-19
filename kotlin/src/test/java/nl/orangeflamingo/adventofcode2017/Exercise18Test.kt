package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise18Test {

    @Test
    fun `test input silver`() {
        val exc18 = Exercise18("/test/eightteen/test18-silver.txt")
        assertEquals(4, exc18.silverExercise18())
    }

    @Test
    fun `actual input silver`() {
        val exc18 = Exercise18("/input/input18.txt")
        assertEquals(7071, exc18.silverExercise18())
    }

    @Test
    fun `test input gold`() {
        val exc18 = Exercise18("/test/eightteen/test18-gold.txt")
        assertEquals(3, exc18.goldExercise18())
    }

    @Test
    fun `actual input gold`() {
        val exc18 = Exercise18("/input/input18.txt")
        assertEquals(0, exc18.goldExercise18())
    }
}