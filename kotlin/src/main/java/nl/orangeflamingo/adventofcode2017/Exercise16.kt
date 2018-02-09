package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise16(fileName: String, var programs: CharArray) {

    private val moves: List<Move> = parseInput(fileName)

    fun silverExercise16(): String {
        moves.forEach({ move -> processMove(move)})
        return programs.joinToString("")
    }

    fun goldExercise16(): String {
        return "TODO"
    }

    private fun processMove(move: Move) {
        when (move) {
            is Spin -> programs = (programs.takeLast(move.size) + programs.dropLast(move.size)).toCharArray()
            is Swap -> {
                val tmp = programs[move.left]
                programs[move.left] = programs[move.right]
                programs[move.right] = tmp
            }
            is Swap2 -> {
                val indexLeft = programs.indexOf(move.left)
                val indexRight = programs.indexOf(move.right)
                val tmp = programs[indexLeft]
                programs[indexLeft] = programs[indexRight]
                programs[indexRight] = tmp
            }
        }
    }

    private fun parseInput(file: String): List<Move> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val movesAsString = inputStream.bufferedReader().readLine().split(",")
        return movesAsString
                .map {
                    if (it.first() == 's') {
                        Spin(it.drop(1).toInt())
                    } else if (it.first() == 'x') {
                        val t = it.drop(1).split("/").map { it.toInt() }
                        Swap(t[0], t[1])
                    } else {
                        val t2 = it.drop(1).split("/")
                        Swap2(t2[0].single(), t2[1].single())
                    }
                }
    }
}


sealed class Move

class Spin(val size: Int) : Move() {


}

class Swap(val left: Int, val right: Int) : Move() {

}

class Swap2(val left: Char, val right: Char) : Move() {

}

fun main(args: Array<String>) {
    val programs = "abcdefghijklmnop".toCharArray()
    val exc16 = Exercise16("/input/input16.txt", programs)
    val answerSilver = exc16.silverExercise16()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc16.goldExercise16()
    println("The answer for the silver exercise is: $answerGold")
}
