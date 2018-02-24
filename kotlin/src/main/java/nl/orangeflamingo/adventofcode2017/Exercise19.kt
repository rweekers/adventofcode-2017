package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise19(fileName: String) {

    private val grid: List<CharArray> = linesAsList(fileName)

    private fun linesAsList(file: String): List<CharArray> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList.map { it.toCharArray() }
    }

    fun silverExercise19(): String {
        return move(Tube(grid[0].indexOf('|'), 0), Direction.DOWN)
    }

    private tailrec fun move(location: Tube,
                             direction: Direction,
                             visitedChars: List<Char> = emptyList()): String =
            if (grid[location.x][location.y] == ' ') visitedChars.joinToString("")
            else {
                when (grid[location.x][location.y]) {
                    in 'A'..'Z' -> move(location.move(direction), direction, visitedChars + grid[location.x][location.y])
                    '+' -> {
                        // TODO implement turning
                        move(location, direction, visitedChars)
                    }
                    else -> move(location.move(direction), direction, visitedChars)
                }
            }

    fun main(args: Array<String>) {
        val exc19Silver = Exercise19("/input/input19.txt")
        val answerSilver = exc19Silver.silverExercise19()
        println("The answer for the silver exercise is: $answerSilver")
    }

    data class Tube(val x: Int, val y: Int) {
        fun move(direction: Direction): Tube =
                when (direction) {
                    Direction.UP -> Tube(x, y - 1)
                    Direction.DOWN -> Tube(x, y + 1)
                    Direction.LEFT -> Tube(x - 1, y)
                    Direction.RIGHT -> Tube(x + 1, y)
                }
    }

    enum class Direction(val axis: String) {
        UP("Y"), DOWN("Y"), LEFT("X"), RIGHT("X")
    }
}
