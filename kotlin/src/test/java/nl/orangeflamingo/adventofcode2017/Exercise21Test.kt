package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise21Test {

    @Test
    fun `test input silver`() {
        val exc21 = Exercise21("/test/twenty_one/test21.txt")
        assertEquals(12, exc21.silverExercise21(2))
    }

    @Test
    fun `actual input silver`() {
        val exc21 = Exercise21("/input/input21.txt")
        assertEquals(164, exc21.silverExercise21())
    }

    @Test
    fun `actual input gold`() {
        val exc21 = Exercise21("/input/input21.txt")
        assertEquals(2355110, exc21.goldExercise21())
    }
}