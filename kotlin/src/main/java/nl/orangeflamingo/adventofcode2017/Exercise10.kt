package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise10(fileName: String) {

    private val cleanedInput = parseAndCleanInput(fileName)

    fun silverExercise10(): Int {
        cleanedInput.forEach({println(it)})
        return 0
    }

    private fun reverseSublist(index: Int, length: Int) {

    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseAndCleanInput(file: String): IntArray {
        return linesAsList(file).first().split(",").map { it.trim().toInt() }.toIntArray()
    }
}

fun main(args: Array<String>) {
    val exc10 = Exercise10("/input/input10.txt")
    val answerSilver = exc10.silverExercise10()
    println("The answer for the silver exercise is: $answerSilver")
}
