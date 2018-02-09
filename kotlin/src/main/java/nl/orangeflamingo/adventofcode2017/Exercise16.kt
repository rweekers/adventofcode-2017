package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise16(fileName: String, private var programs: String) {

    private val moves: List<Move> = parseInput(fileName)

    fun silverExercise16(): String {
        return processMoves()
    }

    tailrec fun goldExercise16(danceFloorStore: Map<String, Int> = mapOf(), iterations: Int = 0, danceFloor: String = programs): String {
        return if (danceFloor in danceFloorStore) {
            val startCycle = danceFloorStore.getValue(danceFloor)
            val offset = (1000000000 % (iterations - startCycle)) - startCycle
            danceFloorStore.entries.first { it.value == offset }.key
        } else {
            goldExercise16(danceFloorStore + (danceFloor to iterations), iterations.inc(), processMoves(danceFloor.toCharArray()))
        }
    }

    private fun processMoves(progs: CharArray = programs.toCharArray()): String {
        return moves.fold(progs) { acc, curr -> processMove(acc, curr) }.joinToString("")
    }

    private fun processMove(prog: CharArray, move: Move): CharArray =
        when (move) {
            is Spin -> (prog.takeLast(move.size) + prog.dropLast(move.size)).toCharArray()
            is Swap -> swap(move.left, move.right, prog)
            is SwapByName -> swap(prog.indexOf(move.left), prog.indexOf(move.right), prog)
        }

    private fun swap(left: Int, right: Int, prog: CharArray): CharArray {
        val tmp = prog[left]
        prog[left] = prog[right]
        prog[right] = tmp
        return prog
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
                        SwapByName(t2[0].single(), t2[1].single())
                    }
                }
    }
}


sealed class Move

class Spin(val size: Int) : Move()

class Swap(val left: Int, val right: Int) : Move()

class SwapByName(val left: Char, val right: Char) : Move()

fun main(args: Array<String>) {
    val programs = "abcdefghijklmnop"
    val exc16 = Exercise16("/input/input16.txt", programs)
    val answerSilver = exc16.silverExercise16()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc16.goldExercise16()
    println("The answer for the gold exercise is: $answerGold")
}
