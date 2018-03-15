package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise25(fileName: String) {

    private val lines = linesAsList(fileName)
    val initialState = lines.first()[15]
    val steps = lines[1].split(" ")[5].toInt()

    val ruleMap = lines
            .filter { it.isNotBlank() }
            .drop(2)
            .map { it.split(" ").last().dropLast(1) }
            .chunked(9)
            .map {
                it[0] to mapOf(it[1].toInt() to Rule(it[2].toInt(), it[3], it[4]), it[5].toInt() to Rule(it[6].toInt(), it[7], it[8]))
            }

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

class Rule(val value: Int, val action: String, val nextState: String)

fun main(args: Array<String>) {
    val exc25Silver = Exercise25("/input/input25.txt")
    val answerSilver = exc25Silver.silverExercise25()
    println("The answer for the silver exercise is: $answerSilver")
    val exc25Gold = Exercise25("/input/input25.txt")
    val answerGold = exc25Gold.goldExercise25()
    println("The answer for the silver exercise is: $answerGold")
}
