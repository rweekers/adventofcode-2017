package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise25(fileName: String) {

    private val lines = parseInput(linesAsList(fileName))

    private fun parseInput(input: List<String>): List<String> =
            input

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise25(): Int {
        return 0
    }

    fun goldExercise25(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    val exc25Silver = Exercise25("/input/input25.txt")
    val answerSilver = exc25Silver.silverExercise25()
    println("The answer for the silver exercise is: $answerSilver")
    val exc25Gold = Exercise25("/input/input25.txt")
    val answerGold = exc25Gold.goldExercise25()
    println("The answer for the silver exercise is: $answerGold")
}
