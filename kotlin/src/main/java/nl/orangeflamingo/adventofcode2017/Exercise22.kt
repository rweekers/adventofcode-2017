package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise22(fileName: String) {

    private val lines: List<String> = linesAsList(fileName)

    private fun linesAsList(file: String): List<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise22(): Int {
        return 0
    }

    fun goldExercise22(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    val exc22Silver = Exercise22("/input/input22.txt")
    val answerSilver = exc22Silver.silverExercise22()
    println("The answer for the silver exercise is: $answerSilver")
    val exc22Gold = Exercise22("/input/input22.txt")
    val answerGold = exc22Gold.goldExercise22()
    println("The answer for the gold exercise is: $answerGold")
}
