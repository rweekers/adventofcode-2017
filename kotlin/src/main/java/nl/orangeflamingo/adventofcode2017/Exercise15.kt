package nl.orangeflamingo.adventofcode2017

import java.time.Duration

class Exercise15(private val startValueA: Long, private val startValueB: Long) {

    private val factorA = 16807
    private val factorB = 48271
    private val divider = 2147483647

    fun silverExercise15(): Int {
        return doWork()
    }

    fun goldExercise15(): Int {
        return doWorkGold()
    }

    private fun formatSolution(numberInput: Long): String {
        val aBinary = numberInput.toString(2)
        val aFormatted = aBinary.padStart(32, '0')
        return aFormatted.substring(16)
    }

    private fun calculateSilver(inputValue: Long, multiplyFactor: Int): Long {
        return inputValue.times(multiplyFactor).rem(divider)
    }

    fun calculateGold(inputValue: Long, multiplyFactor: Int, div: Int): Long {
        var answer = calculateSilver(inputValue, multiplyFactor)
        while (true) {
            if (answer.rem(div) == 0L) {
                return answer
            }
            answer = calculateSilver(answer, multiplyFactor)
        }
    }

    private fun doWork(): Int {
        val startTime = System.currentTimeMillis()

        var aNumeric: Long = startValueA
        var bNumeric: Long = startValueB
        var timesFound = 0
        (1..40000000).forEach {
            aNumeric = calculateSilver(aNumeric, factorA)
            bNumeric = calculateSilver(bNumeric, factorB)
            if (formatSolution(aNumeric) == formatSolution(bNumeric)) {
                timesFound++
            }
        }
        val endTime = System.currentTimeMillis()
        val duration = Duration.ofMillis(endTime - startTime)
        println("Found answer in ${duration.seconds} seconds")
        return timesFound
    }

    private fun doWorkGold(): Int {
        val startTime = System.currentTimeMillis()

        var aNumeric: Long = startValueA
        var bNumeric: Long = startValueB
        var timesFound = 0
        (1..5000000).forEach {
            aNumeric = calculateGold(aNumeric, factorA, 4)
            bNumeric = calculateGold(bNumeric, factorB, 8)
            if (formatSolution(aNumeric) == formatSolution(bNumeric)) {
                timesFound++
            }
        }
        val endTime = System.currentTimeMillis()
        val duration = Duration.ofMillis(endTime - startTime)
        println("Found answer in ${duration.seconds} seconds")
        return timesFound
    }
}

fun main(args: Array<String>) {
    val exc15 = Exercise15(873, 583)
    val answerSilver = exc15.silverExercise15()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc15.goldExercise15()
    println("The answer for the silver exercise is: $answerGold")
}
