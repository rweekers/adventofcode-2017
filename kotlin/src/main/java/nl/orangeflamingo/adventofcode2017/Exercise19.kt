package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class Exercise19(fileName: String) {

    private val grid: List<CharArray> = linesAsList(fileName)

    private fun linesAsList(file: String): List<CharArray> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList.map { it.toCharArray() }
    }

    fun silverExercise19(): String {
        return "TODO"
    }
}

fun main(args: Array<String>) {
    val exc19Silver = Exercise19("/input/input19.txt")
    val answerSilver = exc19Silver.silverExercise19()
    println("The answer for the silver exercise is: $answerSilver")
}
