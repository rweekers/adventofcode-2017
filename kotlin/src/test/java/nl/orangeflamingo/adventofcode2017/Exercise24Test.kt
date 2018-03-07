package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise24Test {

    @Test
    fun `test input silver`() {
        val exc24 = Exercise24("/test/twenty_four/test24.txt")
        assertEquals(31, exc24.silverExercise24())
    }

    @Test
    fun `actual input silver`() {
        val exc24 = Exercise24("/input/input24.txt")
        assertEquals(1695, exc24.silverExercise24())
    }

    @Test
    fun `test input gold`() {
        val exc24 = Exercise24("/test/twenty_four/test24.txt")
        assertEquals(19, exc24.goldExercise24())
    }

    @Test
    fun `actual input gold`() {
        val exc24 = Exercise24("/input/input24.txt")
        assertEquals(1673, exc24.goldExercise24())
    }
}