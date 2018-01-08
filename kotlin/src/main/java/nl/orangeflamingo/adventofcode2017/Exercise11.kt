package nl.orangeflamingo.adventofcode2017

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream
import kotlin.math.absoluteValue

class Exercise11(fileName: String) {

    private val steps = parseAndCleanInputExcSilver(fileName)

    fun silverExercise11(): Int {
        var distance = 0
        steps.toObservable()
                .reduce(Hexagon(0, 0, 0), { acc, cur -> acc.doMove(cur) })
                .map { x -> x.calculateDistance() }
                .subscribe({ x -> distance = x })
        return distance
    }

    fun goldExercise11(): Int {
        var distance = 0
        steps.toObservable()
                .scan(Hexagon(0, 0, 0), { acc, cur -> acc.doMove(cur) })
                .map { x -> x.calculateDistance() }
                .reduce( { acc, cur -> if (cur > acc) cur else acc } )
                .subscribe({ x -> distance = x })
        return distance
    }

    private fun parseAndCleanInputExcSilver(file: String): List<String> {
        return linesAsList(file).first().split(",")
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }
}

data class Hexagon(val x: Int, val y: Int, val z: Int) {
    fun doMove(direction: String): Hexagon {
        return when (direction) {
            "n" -> Hexagon(x, y + 1, z - 1)
            "s" -> Hexagon(x, y - 1, z + 1)
            "ne" -> Hexagon(x + 1, y, z - 1)
            "nw" -> Hexagon(x - 1, y + 1, z)
            "se" -> Hexagon(x + 1, y - 1, z)
            "sw" -> Hexagon(x - 1, y, z + 1)
            else -> throw IllegalArgumentException("$direction is unknown")
        }
    }

    fun calculateDistance(): Int {
        return maxOf(
                this.x.absoluteValue,
                this.y.absoluteValue,
                this.z.absoluteValue
        )
    }
}

fun main(args: Array<String>) {
    val answerSilver = Exercise11("/input/input11.txt").silverExercise11()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = Exercise11("/input/input11.txt").goldExercise11()
    println("The answer for the gold exercise is: $answerGold")
}
