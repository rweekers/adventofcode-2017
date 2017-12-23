package nl.orangeflamingo.adventofcode2017.six

import java.io.InputStream
import java.lang.Integer.parseInt

class Exercise6 {

    private val evaluatedValues: MutableList<String> = mutableListOf()
    private var count: Int = 0

    private fun linesAsList(file: String):MutableList<Int> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        val numberList: MutableList<Int> = mutableListOf()

        lineList.flatMap { line -> line.split("\t") }
            .forEach( { item -> numberList.add(parseInt(item)) } )
        return numberList
    }

    private fun getLargestValue(list: List<Int>): Pair<Int, Int> {
        val maxValue: Int = list.stream().max { o1, o2 -> Integer.compare(o1, o2) }
                .orElseThrow({ RuntimeException("Largest number not found")})
        val maxIndex = list.indexOf(maxValue)
        if (maxIndex == -1) throw java.lang.RuntimeException("Something is messed up, index of largest number not found")
        return Pair(maxIndex, maxValue)
    }

    private fun updateList(list: MutableList<Int>, pair: Pair<Int, Int>): MutableList<Int> {
        // rotate through list with value to spread
        var value = pair.second
        var index = if (pair.first.plus(1) < list.size) pair.first.plus(1) else 0
        val listLength = list.size
        list[pair.first] = 0
        while (value > 0) {
            list[index] += 1
            index = getIndexToUpdate(index, listLength)
            value--
        }
        return list
    }

    private fun getListAsString(list: List<Int>): String {
        var value = ""
        list.forEach( {
            item -> value = "$value$item"
        })
        return value
    }

    private fun getIndexToUpdate(start: Int, listLength: Int): Int {
        return if (start == listLength - 1) 0 else start + 1
    }

    fun silverExercise6(file: String): Int {
        val numberList = linesAsList(file)
        while (!evaluatedValues.contains(getListAsString(numberList))) {
            evaluatedValues.add(getListAsString(numberList))
            val pair = getLargestValue(numberList)
            updateList(numberList, pair)
            count++
        }
        evaluatedValues.add(getListAsString(numberList))
        return count
    }

    fun goldExercise6(): Int {
        val last = evaluatedValues.last()
        val firstIndex = evaluatedValues.indexOf(last)
        val lastIndex = evaluatedValues.size - 1
        return lastIndex - firstIndex
    }
}

fun main(args: Array<String>) {
    val exc6 = Exercise6()
    val numberOfStepsSilver = exc6.silverExercise6("/input6.txt")
    println("Total correct silver number of steps: $numberOfStepsSilver")
    val answerGold = exc6.goldExercise6()
    println("Total correct gold number of steps: $answerGold")

}
