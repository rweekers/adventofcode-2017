package nl.orangeflamingo.adventofcode2017

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise10(fileName: String, length: Int) {

    private val inputLengthsSilver = parseAndCleanInputExcSilver(fileName)
    private val inputLengthsGold = parseAndCleanInputExcGold(fileName)
    private val circularListSilver = CircularList(length)
    private val circularListGold = CircularList(length)

    fun silverExercise10(): Int {
        inputLengthsSilver.forEach { doWorkSilver(it) }
        return circularListSilver.getScoreSilver()
    }

    fun goldExercise10(): String {
        repeat(64, { inputLengthsGold.forEach { doWorkGold(it) } })
        return circularListGold.getScoreGold()
    }

    private fun doWorkSilver(inputLength: Int) {
        circularListSilver.reverseSublist(inputLength)
    }

    private fun doWorkGold(inputLength: Int) {
        circularListGold.reverseSublist(inputLength)
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseAndCleanInputExcSilver(file: String): IntArray {
        return linesAsList(file).first().split(",").map { it.trim().toInt() }.toIntArray()
    }

    private fun parseAndCleanInputExcGold(file: String): IntArray {
        return linesAsList(file).first().map { it.toInt() }.plus(listOf(17, 31, 73, 47, 23)).toIntArray()
    }
}

data class CircularList(private val length: Int) {

    private val theList = IntArray(length, { i -> i })
    private var currentIndex = 0
    private var skip = 0

    fun reverseSublist(length: Int) {
        val subListReversed = getSublist(length).reversedArray()
        replaceSublist(length, subListReversed)
    }

    fun getScoreSilver(): Int {
        return theList[0] * theList[1]
    }

    fun getScoreGold(): String {
        var denseHash = String()
        theList.toObservable()
                .buffer(16)
                .map( { a -> hashify(a) } )
                .reduce( { acc, cur -> acc + cur } )
                .subscribe( { x -> denseHash = x } )
        return denseHash
    }

    private fun hashify(list: List<Int>): String {
        var hash = String()
        list.toObservable()
                .reduce( { acc, curr -> acc.xor(curr) } )
                .map { x -> Integer.toHexString(x) }
                .subscribe( { x -> hash = x } )
        return hash
    }

    private fun getSublist(length: Int): IntArray {
        if (currentIndex + length <= theList.size) {
            return theList.sliceArray(IntRange(currentIndex, currentIndex + length - 1))
        }
        val lastPart = theList.sliceArray(IntRange(currentIndex, theList.size - 1))
        val firstPart = theList.sliceArray(IntRange(0, ((currentIndex + length) % theList.size) - 1))
        return lastPart.plus(firstPart)
    }

    private fun replaceSublist(length: Int, subListReversed: IntArray) {
        var index = currentIndex
        subListReversed.forEach {
            theList[getCircularIndex(index)] = it
            index++
        }
        currentIndex = getCircularIndex(currentIndex + length + skip)
        skip++
    }

    private fun getCircularIndex(index: Int): Int {
        if (index >= theList.size) {
            return index % theList.size
        }
        return index
    }
}

fun main(args: Array<String>) {
    val exc10 = Exercise10("/input/input10.txt", 256)
    val answerSilver = exc10.silverExercise10()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc10.goldExercise10()
    println("The answer for the gold exercise is: $answerGold")
}
