package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise16(fileName: String) {

    private val input = parseInput(fileName)

    fun silverExercise16(): String {
        return input
    }

    fun goldExercise16(): String {
        return "TODO"
    }

    private fun parseInput(file: String): String {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        return inputStream.bufferedReader().readLine()
    }
}

fun main(args: Array<String>) {
    val exc16 = Exercise16("/input/input16.txt")
    val answerSilver = exc16.silverExercise16()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc16.goldExercise16()
    println("The answer for the silver exercise is: $answerGold")
}
