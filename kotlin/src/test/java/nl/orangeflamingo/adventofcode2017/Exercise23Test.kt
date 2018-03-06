package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise23Test {

    @Test
    fun `actual input silver`() {
        val exc23 = Exercise23("/input/input23.txt")
        assertEquals(6241, exc23.silverExercise23())
    }

    @Test
    fun `actual input gold`() {
        val exc23 = Exercise23("/input/input23.txt")
        assertEquals(909, exc23.goldExercise23())
    }
}