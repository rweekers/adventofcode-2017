package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise21(fileName: String) {

    private val rowSplit = " => ".toRegex()
    private val transforms: Map<FractalGrid, FractalGrid> = parseInput(linesAsList(fileName))
    private val startPattern = FractalGrid(".#./..#/###")

    private fun parseInputRow(input: String): Pair<FractalGrid, FractalGrid> =
            input.split(rowSplit)
                    .map { FractalGrid(it) }
                    .let { it.first() to it.last() }


    private fun parseInput(input: List<String>): Map<FractalGrid, FractalGrid> =
            input.map { parseInputRow(it) }.flatMap { pair ->
                pair.first.combinations().map { combo ->
                    combo to pair.second
                }
            }.toMap()

    private fun linesAsList(file: String): List<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise21(iterations: Int = 5): Int {
        return run(iterations)
    }

    fun goldExercise21(): Int {
        return run(18)
    }

    private fun run(iterations: Int): Int {
        var grid = startPattern
        repeat(iterations) {
            val splits = grid.split()
            val next = splits.map { transforms.getValue(it) }
            grid = next.join()
        }
        return grid.toString().count { it == '#' }
    }

    private fun List<FractalGrid>.join(): FractalGrid {
        val rows = Math.sqrt(this.size.toDouble()).toInt()
        return this.chunked(rows).map {
            it.reduce { a, b -> a.besides(b) }
        }.reduce { a, b -> a.above(b) }
    }
}

fun main(args: Array<String>) {
    val exc21Silver = Exercise21("/input/input21.txt")
    val answerSilver = exc21Silver.silverExercise21()
    println("The answer for the silver exercise is: $answerSilver")
    val exc21Gold = Exercise21("/input/input21.txt")
    val answerGold = exc21Gold.goldExercise21()
    println("The answer for the silver exercise is: $answerGold")
}

data class FractalGrid(private val grid: List<List<Char>>) {

    val size = grid.size

    constructor(input: String) : this(
            input.split("/").map { it.toList() }
    )

    private fun rotate(): FractalGrid =
            FractalGrid(
                    (0 until grid.size).map { row ->
                        (0 until grid.size).map { column ->
                            grid[column][(grid.size) - 1 - row]
                        }
                    }
            )

    private fun flip(): FractalGrid =
            FractalGrid(grid.map { it.reversed() })

    fun combinations(): List<FractalGrid> {
        val rotations = (1..3).fold(listOf(this)) { r, _ -> r + r.last().rotate() }
        val flips = rotations.map { it.flip() }
        return rotations + flips
    }

    private fun rowsOfSize(n: Int): List<FractalGrid> =
            this.grid.chunked(n).map { FractalGrid(it) }

    private fun columnsOfSize(n: Int): List<FractalGrid> {
        val chopped = this.grid.map { row ->
            row.chunked(n)
        }
        return (0 until (grid[0].size) / n).map { x ->
            (0 until n).map { y ->
                chopped[y][x]
            }
        }.map { FractalGrid(it) }
    }

    fun split(): List<FractalGrid> {
        val splitSize = if (size % 2 == 0) 2 else 3
        val splits = size / splitSize
        if (splits == 1) {
            return listOf(this)
        }
        return (rowsOfSize(splitSize)).map { it.columnsOfSize(splitSize) }.flatten()
    }

    fun besides(that: FractalGrid): FractalGrid =
            FractalGrid(
                    grid.mapIndexed { idx, row -> row + that.grid[idx] }
            )

    fun above(that: FractalGrid): FractalGrid =
            FractalGrid(
                    this.grid + that.grid
            )
}
