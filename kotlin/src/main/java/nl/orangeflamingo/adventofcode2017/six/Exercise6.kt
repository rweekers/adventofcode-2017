package nl.orangeflamingo.adventofcode2017.six

import java.io.InputStream
import java.lang.Integer.parseInt

class Exercise6 {

    private fun linesAsList(file: String):List<Int> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        val numberList: MutableList<Int> = mutableListOf()

        lineList.flatMap { line -> line.split("\t") }
            .forEach( { item -> numberList.add(parseInt(item)) } )
        return numberList
    }

    fun silverExercise5(file: String): Int {
        for (line in linesAsList(file)) {
            println(line)
        }
        return 0
    }
}

fun main(args: Array<String>) {
    val numberOfStepsSilver = Exercise6().silverExercise5("/input5.txt")
    println("Total correct silver number of steps: $numberOfStepsSilver")
}
