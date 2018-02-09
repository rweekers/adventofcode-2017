package nl.orangeflamingo.adventofcode2017

import org.junit.Test
import kotlin.test.assertEquals

class Exercise16Test {

    private val exc16 = Exercise16("/test/sixteen/test16.txt", "abcde".toCharArray())

    @Test
    fun exercise16SilverTest() {
        assertEquals("baedc", exc16.silverExercise16())
    }

    @Test
    fun exercise15GoldTest() {
        assertEquals("to be determined", exc16.goldExercise16())
    }
}