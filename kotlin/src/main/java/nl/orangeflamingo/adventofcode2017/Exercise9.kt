package nl.orangeflamingo.adventofcode2017

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise9(fileName: String) {

    private val cleanedInput = parseAndCleanInput(fileName)
    private val garbage = "<.*?>".toRegex()
    private val nonGroup = "[^{}]".toRegex()

    fun silverExercise9(): Int {
        val withoutGarbage = cleanedInput.replace(garbage, "").replace(nonGroup, "")
        var totalScore: Int = 0
        withoutGarbage.asIterable().toObservable()
                .reduce(Score(0, 0), { acc, cur -> acc.addSign(cur) })
                .map { it.getTotalScore() }
                .subscribe({ it -> totalScore = it })
        return totalScore
    }

    fun goldExercise9(): Int {
        return 0
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseAndCleanInput(file: String): String {
        val list = linesAsList(file).first()
        val exclamation = "!.".toRegex()
        return list.replace(exclamation, "")
    }
}

data class Score(private val level: Int, private val score: Int) {

    fun getTotalScore(): Int {
        return score
    }

    fun addSign(sign: Char): Score {
        if (sign.toString() == "{") {
            return Score(level + 1, score)
        }
        return Score(level - 1, score + level)
    }
}

fun main(args: Array<String>) {
    val exc9 = Exercise9("/input/input9.txt")
    val answerSilver = exc9.silverExercise9()
    println("The largest value after running the operations for the silver exercise is: $answerSilver")
    val answerGold = exc9.goldExercise9()
    println("The answer for the gold exercise is: $answerGold")
}
