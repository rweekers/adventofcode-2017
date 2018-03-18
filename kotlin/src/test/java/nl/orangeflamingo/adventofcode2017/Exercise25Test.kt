package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise25Test {

    @Test
    fun `test input silver`() {
        val exc25 = Exercise25("/test/twenty_five/test25.txt")
        assertEquals(3, exc25.silverExercise25())
    }

    @Test
    fun `actual input silver`() {
        val exc25 = Exercise25("/input/input25.txt")
        assertEquals(4230, exc25.silverExercise25())
    }
}