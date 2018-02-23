package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise19Test {

    @Test
    fun `test input silver`() {
        val exc19 = Exercise19("/test/nineteen/test19.txt")
        assertEquals("ABCDEF", exc19.silverExercise19())
    }

    @Test
    fun `actual input silver`() {
        val exc19 = Exercise19("/input/input19.txt")
        assertEquals("?", exc19.silverExercise19())
    }
}