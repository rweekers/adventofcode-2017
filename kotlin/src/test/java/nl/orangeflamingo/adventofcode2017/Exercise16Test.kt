package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise16Test {

    private val actualProgram = "abcdefghijklmnop"

    @Test
    fun `test input silver`() {
        val exc16 = Exercise16("/test/sixteen/test16.txt", "abcde")
        assertEquals("baedc", exc16.silverExercise16())
    }

    @Test
    fun `actual input silver`() {
        val exc16 = Exercise16("/input/input16.txt", actualProgram)
        assertEquals("dcmlhejnifpokgba", exc16.silverExercise16())
    }

    @Test
    fun `actual input gold`() {
        val exc16 = Exercise16("/input/input16.txt", actualProgram)
        assertEquals("ifocbejpdnklamhg", exc16.goldExercise16())
    }
}