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
        return move(Tube(grid[0].indexOf('|'), 0), Direction.DOWN).first
    }

    fun goldExercise19(): Int {
        return move(Tube(grid[0].indexOf('|'), 0), Direction.DOWN).second
    }

    private tailrec fun move(location: Tube,
                             direction: Direction,
                             visitedChars: List<Char> = emptyList(),
                             numberOfSteps: Int = 0): Pair<String, Int> =
            if (charAt(location.x, location.y, grid) == ' ') Pair(visitedChars.joinToString(""), numberOfSteps)
            else {
                when (charAt(location.x, location.y, grid)) {
                    in 'A'..'Z' -> move(location.move(direction), direction, visitedChars + charAt(location.x, location.y, grid), numberOfSteps.inc())
                    '+' -> {
                        val newDirection = turn(location, direction)
                        move(location.move(newDirection), newDirection, visitedChars, numberOfSteps.inc())
                    }
                    else -> move(location.move(direction), direction, visitedChars, numberOfSteps.inc())
                }
            }

    private fun charAt(x: Int, y: Int, grid: List<CharArray>): Char {
        if (y >= grid.size || x >= grid[y].size) return ' '
        return grid[y][x]
    }

    private fun turn(location: Tube, direction: Direction): Direction {
        return direction.perpendicularDirections()
                .filter {
                    val loc = location.move(it)
                    charAt(loc.x, loc.y, grid) != ' '
                }
                .first()
    }
}

fun main(args: Array<String>) {
    val exc19Silver = Exercise19("/input/input19.txt")
    val answerSilver = exc19Silver.silverExercise19()
    println("The answer for the silver exercise is: $answerSilver")
    val exc19Gold = Exercise19("/input/input19.txt")
    val answerGold = exc19Gold.goldExercise19()
    println("The answer for the silver exercise is: $answerGold")
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
    UP("Y"), DOWN("Y"), LEFT("X"), RIGHT("X");

    fun perpendicularDirections(): List<Direction> {
        if (this.axis == "X") {
            return listOf(UP, DOWN)
        }
        return listOf(LEFT, RIGHT)
    }
}
