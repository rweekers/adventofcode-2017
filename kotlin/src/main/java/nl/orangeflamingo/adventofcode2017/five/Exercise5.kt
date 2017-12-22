package nl.orangeflamingo.adventofcode2017.five

import java.io.InputStream
import java.lang.Integer.parseInt
import java.lang.Math.abs

class Exercise5 {

    var count: Int = 0

    private fun linesAsList(file: String): MutableList<Int> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<Int>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(parseInt(it)) } }
        return lineList
    }

    fun silverExercise5(file: String): Int {
        doSilverMove(linesAsList(file))
        return this.count
    }

    fun goldExercise5(file: String): Int {
        doGoldMove(linesAsList(file))
        return this.count
    }

    private fun doSilverMove(list: MutableList<Int>) {
        var index = 0
        while (index >= 0 && index < list.size) {
            val value = list[index]
            val newValue = value + 1
            list[index] = newValue
            count++
            index += value
        }
    }

    private fun doGoldMove(list: MutableList<Int>) {
        var index = 0
        while (index >= 0 && index < list.size) {
            val value = list[index]
            val increment = if (value >= 3) -1 else 1
            val newValue = value + increment
            list[index] = newValue
            count++
            index += value
        }
    }
}

fun main(args: Array<String>) {
    val numberOfStepsSilver = Exercise5().silverExercise5("/input5.txt")
    val numberOfStepsGold = Exercise5().goldExercise5("/input5.txt")
    println("Total correct silver number of steps: $numberOfStepsSilver")
    println("Total correct gold number of steps: $numberOfStepsGold")
}
