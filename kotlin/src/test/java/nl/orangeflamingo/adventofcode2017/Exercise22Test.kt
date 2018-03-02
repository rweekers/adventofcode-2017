package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise22Test {

    @Test
    fun `test input silver`() {
        val exc22 = Exercise22("/test/twenty_two/test22.txt")
        assertEquals(5587, exc22.silverExercise22())
    }

    @Test
    fun `actual input silver`() {
        val exc22 = Exercise22("/input/input22.txt")
        assertEquals(5259, exc22.silverExercise22())
    }

    @Test
    fun `test input gold`() {
        val exc22 = Exercise22("/test/twenty_two/test22.txt")
        assertEquals(2511944, exc22.goldExercise22())
    }

    @Test
    fun `actual input gold`() {
        val exc22 = Exercise22("/input/input22.txt")
        assertEquals(2511722, exc22.goldExercise22())
    }
}