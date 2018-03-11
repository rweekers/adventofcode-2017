package nl.orangeflamingo.adventofcode2017

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise24(fileName: String) {

    private val components = parseInput(linesAsList(fileName))

    private fun parseInput(input: List<String>): List<Component> =
            input.map { it.split("/") }.map { Component(it[0].toInt(), it[1].toInt()) }.toList()

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun possibleBridges(components: List<Component>,
                                bridge: List<Component> = emptyList(),
                                port: Int = 0): List<List<Component>> {
        val compatibleParts = components.filter { it.canLinkTo(port) }
        return when (compatibleParts.isEmpty()) {
            true -> listOf(bridge)
            false -> compatibleParts
                    .flatMap { candidate -> possibleBridges(components - candidate, bridge + candidate, candidate.oppositePort(port)) }
        }
    }

    fun silverExercise24(): Int {
        return possibleBridges(components).toObservable().reduce { acc, curr -> if (curr.sumBy { it.strength } > acc.sumBy { it.strength }) curr else acc }.map { x -> x.sumBy { it.strength } }.blockingGet()
    }

    fun goldExercise24(): Int {
        return possibleBridges(components).toObservable()
                .reduce { acc, curr -> if (curr.largerThan(acc)) curr else acc }
                .map { x -> x.sumBy { it.strength } }.blockingGet()
    }

    private fun List<Component>.largerThan(other: List<Component>): Boolean {
        if (this.size > other.size) return true
        if (this.size == other.size) return this.sumBy { it.strength } > other.sumBy { it.strength }
        return false
    }
}

data class Component(private val portA: Int, private val portB: Int) {
    val strength = portA + portB

    fun canLinkTo(port: Int): Boolean {
        return portA == port || portB == port
    }

    fun oppositePort(port: Int): Int {
        return if (port == portA) portB else portA
    }
}

fun main(args: Array<String>) {
    val exc24Silver = Exercise24("/input/input24.txt")
    val answerSilver = exc24Silver.silverExercise24()
    println("The answer for the silver exercise is: $answerSilver")
    val exc24Gold = Exercise24("/input/input24.txt")
    val answerGold = exc24Gold.goldExercise24()
    println("The answer for the silver exercise is: $answerGold")
}
