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

    private fun makeBridges(components: List<Component>,
                            bridge: List<Component> = emptyList(),
                            port: Int = 0): List<List<Component>> {
        val compatible = components.filter { it.canLink(port) }
        return when (compatible.size) {
            0 -> listOf(bridge)
            else ->
                compatible.flatMap { pick ->
                    makeBridges(
                            components - pick,
                            bridge + pick,
                            pick.opposite(port)
                    )
                }
        }
    }

    fun silverExercise24(): Int {
        return makeBridges(components).toObservable().reduce{ acc, curr -> if(curr.sumBy { it.strength } > acc.sumBy { it.strength }) curr else acc}.map{ x -> x.sumBy { it.strength }}.blockingGet()
    }

    fun goldExercise24(): Int {
        val answer = makeBridges(components).toObservable()
                .reduce { acc, curr -> if (curr.size > acc.size) curr else acc }
                .map { x -> x.sumBy { it.strength } }.blockingGet()

        val answer2 = makeBridges(components)
                .maxWith(
                        compareBy({ it.size }, { it.sumBy { it.strength } })
                )?.sumBy { it.strength }!!

        println("Gold answer reactive is $answer and answer functional is $answer2")

        return answer2
    }
}

data class Component(private val portA: Int, private val portB: Int) {
    val strength = portA + portB

    fun canLink(port: Int): Boolean {
        return portA == port || portB == port
    }

    fun opposite(port: Int): Int {
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
