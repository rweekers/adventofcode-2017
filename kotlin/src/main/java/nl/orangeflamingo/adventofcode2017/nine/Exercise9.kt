package nl.orangeflamingo.adventofcode2017.nine

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise9 (fileName: String) {

    val cleanedInput = parseInput(fileName)

    fun silverExercise9(): Int {
        var totalScore: Int = 0
        cleanedInput.asIterable().toObservable()
                .reduce(Score(0, 0), { acc, cur -> acc.addSign(cur) })
                .map { it.getTotalScore() }
                .subscribe( { it -> totalScore = it })
        return totalScore
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseInput(file: String): String {
        val list = linesAsList(file).first()
        val exclamation = "!.".toRegex()
        val garbage = "<.*?>".toRegex()
        val nonGroup = "[^{}]".toRegex()
        val cleanInput = list.replace(exclamation, "")
        return cleanInput.replace(garbage, "").replace(nonGroup, "")
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
    val exc8 = Exercise9("/input9.txt")
    val answerSilver = exc8.silverExercise9()
    println("The largest value after running the operations for the silver exercise is: $answerSilver")
}
