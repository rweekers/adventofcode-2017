package nl.orangeflamingo.adventofcode2017.nine

import org.junit.Assert
import org.junit.Test


class Exercise9Test {

    @Test
    fun exercise8SilverTest() {
        val exc9 = Exercise8("/test9.txt")
        val answer = exc9.silverExercise9()
        Assert.assertEquals("{{}{}{}{}}", answer)
    }
}