package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import java.math.BigInteger

class Exercise14(fileName: String) {

    private val inputList = parseInput(fileName)
    private val grid = convertToArray()

    fun silverExercise14(): Int {
        return inputList.sumBy { it.count { it == '1' } }
    }

    fun goldExercise14(): Int {
        var groups = 0
        grid.forEachIndexed { x, row ->
            row.forEachIndexed { y, spot ->
                if (spot == 1) {
                    groups += 1
                    markNeighbors(x, y)
                }
            }
        }
        return groups
    }

    private fun markNeighbors(x: Int, y: Int) {
        if (grid[x][y] == 1) {
            grid[x][y] = 2
            neighborsOf(x, y).forEach {
                markNeighbors(it.first, it.second)
            }
        }
    }

    private fun neighborsOf(x: Int, y: Int): List<Pair<Int, Int>> =
            listOf(Pair(x - 1, y), Pair(x + 1, y), Pair(x, y - 1), Pair(x, y + 1))
                    .filter { it.first in 0..127 }
                    .filter { it.second in 0..127 }

    private fun convertToArray(): List<IntArray> {
        return inputList
                .map { s -> s.map { translate(it) } }
                .map { it.toIntArray() }
    }

    private fun translate(c: Char): Int {
        return if (c == '1') 1 else 0
    }

    private fun parseInput(file: String): List<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val inputString = inputStream.bufferedReader().readLine()
        return (0..127)
                .map { KnotHash.knotHash("$inputString-$it") }
                .map { BigInteger(it, 16).toString(2).padStart(128, '0') }
    }
}

class KnotHash {

    companion object {

        private val prefix = listOf(17, 31, 73, 47, 23)

        fun knotHash(inputString: String): String {
            return runForLengths((inputString.map { it.toInt() } + prefix).toIntArray()).toList()
                    .chunked(16)
                    .joinToString("") { toHexadecimal(xor(it), 2) }
        }

        private fun xor(value: List<Int>): Int {
            return value.reduce( { a, b -> a xor b } )
        }

        private fun toHexadecimal(value: Int, width: Int = 1): String {
            return "%0${width}x".format(value)
        }

        private fun runForLengths(lengths: IntArray): IntArray {
            val ring = IntArray(256) { it }
            var position = 0
            var skip = 0
            repeat(64) {
                lengths.forEach { length ->
                    reverseSection(ring, position, length)
                    position = (position + length + skip) % ring.size
                    skip += 1
                }
            }
            return ring
        }

        private fun reverseSection(ring: IntArray, from: Int, length: Int) {
            var fromIdx = from % ring.size
            var toIdx = (fromIdx + length - 1) % ring.size
            repeat(length / 2) {
                ring.swapElements(fromIdx, toIdx)
                fromIdx = fromIdx.inc() % ring.size
                toIdx = toIdx.dec().takeIf { it >= 0 } ?: ring.size - 1
            }
        }

        private fun IntArray.swapElements(a: Int, b: Int): IntArray {
            val tmp = this[a]
            this[a] = this[b]
            this[b] = tmp
            return this
        }
    }
}

fun main(args: Array<String>) {
    val exc14 = Exercise14("/input/input14.txt")
    val answerSilver = exc14.silverExercise14()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc14.goldExercise14()
    println("The answer for the gold exercise is: $answerGold")
}
