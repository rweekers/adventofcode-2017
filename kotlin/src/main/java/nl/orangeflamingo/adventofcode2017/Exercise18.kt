package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise18(fileName: String) {

    init {
        parseInput(fileName)
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise18(): Int {
        return 0
    }

    private fun parseInput(file: String) {
        val list = linesAsList(file)

        val regExp = """\S+""".toRegex()
        list.forEach({ it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val part3 = if (parts.size == 3) parts[2] else "nothing"
            println("The line is divided in ${parts[0]}, ${parts[1]} and $part3")
        })
    }
}

fun main(args: Array<String>) {
    val exc18 = Exercise18("/input/input18.txt")
    val answerSilver = exc18.silverExercise18()
    println("The answer for the silver exercise is: $answerSilver")
}
