package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise22(fileName: String) {

    private val input = linesAsList(fileName)
    private val grid = parseInput(input)
    private var current = Coordinate(input.size / 2, input.first().length / 2)

    private val directions = listOf(Coordinate(0, -1), Coordinate(1, 0), Coordinate(0, 1), Coordinate(-1, 0))
    private var pointing = 0

    private fun left(): Int =
            if (pointing == 0) 3 else pointing - 1

    private fun right(): Int =
            pointing.inc() % 4

    private fun reverse(): Int =
            (pointing + 2) % 4

    private fun linesAsList(file: String): List<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseInput(input: List<String>): MutableMap<Coordinate, NodeState> {
        val destination = mutableMapOf<Coordinate, NodeState>()
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                destination[Coordinate(x, y)] = if (char == '#') NodeState.Infected else NodeState.Clean
            }
        }
        return destination
    }

    fun silverExercise22(iterations: Int = 10000): Int {
        var infectionsCaused = 0
        repeat(iterations) {
            if (grid.getOrDefault(current, NodeState.Clean) == NodeState.Clean) {
                infectionsCaused += 1
                grid[current] = NodeState.Infected
                pointing = left()
            } else {
                grid[current] = NodeState.Clean
                pointing = right()
            }
            current += directions[pointing]
        }
        return infectionsCaused
    }

    fun goldExercise22(iterations: Int = 10000000): Int {
        var infectionsCaused = 0
        repeat(iterations) {
            when (grid.getOrDefault(current, NodeState.Clean)) {
                NodeState.Clean -> {
                    pointing = left()
                    grid[current] = NodeState.Weakened
                }
                NodeState.Weakened -> {
                    infectionsCaused += 1
                    grid[current] = NodeState.Infected
                }
                NodeState.Infected -> {
                    pointing = right()
                    grid[current] = NodeState.Flagged
                }
                NodeState.Flagged -> {
                    pointing = reverse()
                    grid[current] = NodeState.Clean
                }
            }
            current += directions[pointing]
        }
        return infectionsCaused
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

data class Coordinate(val x: Int, val y: Int) {
    operator fun plus(that: Coordinate): Coordinate =
            Coordinate(x + that.x, y + that.y)
}

enum class NodeState {
    Clean,
    Infected,
    Weakened,
    Flagged
}
