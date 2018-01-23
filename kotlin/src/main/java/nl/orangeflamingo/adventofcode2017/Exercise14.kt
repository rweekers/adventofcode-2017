package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise14(fileName: String) {

    private val inputString = parseInput(fileName)

    fun silverExercise14(): Int {
        println("Input string is $inputString")
        return 0
    }

    private fun parseInput(file: String): String {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val inputString = inputStream.bufferedReader().readLine()
        val inputList = (0..127)
                .map { knotHash("$inputString-$it") }
                .map { it.toInt(16).toString(2).padStart(128, '0') }
        return inputString
    }

    private fun knotHash(input: String): String {
        // TODO implement/call KnotHash (see exercise 10)
        return "12345678"
    }
}

fun main(args: Array<String>) {
    val exc14 = Exercise14("/input/input14.txt")
    val answerSilver = exc14.silverExercise14()
    println("The answer for the silver exercise is: $answerSilver")
}
