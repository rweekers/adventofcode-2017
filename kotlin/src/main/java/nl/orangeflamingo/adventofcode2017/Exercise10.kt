package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise10(fileName: String, length: Int) {

    private val inputLengths = parseAndCleanInput(fileName)
    private val circularList = CircularList(length)

    fun silverExercise10(): Int {
        inputLengths.forEach { doWork(it) }
        return circularList.getScore()
    }

    private fun doWork(inputLength: Int) {
        circularList.reverseSublist(inputLength)
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseAndCleanInput(file: String): IntArray {
        return linesAsList(file).first().split(",").map { it.trim().toInt() }.toIntArray()
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

    fun getScore(): Int {
        return theList[0] * theList[1]
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
}
