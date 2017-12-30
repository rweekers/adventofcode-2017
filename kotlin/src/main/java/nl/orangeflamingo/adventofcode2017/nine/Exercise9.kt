package nl.orangeflamingo.adventofcode2017.nine

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise8 (fileName: String) {

    val cleanedInput = parseInput(fileName)

    fun silverExercise9(): String {
        // fix reactive?
        cleanedInput.asIterable().toObservable()
                .scan( "<seed class>", {acc, cur -> acc } )
                .subscribe( { println(it) })
        return cleanedInput
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun parseInput(file: String): String {
        val list = linesAsList(file).first()
        val cancel = "!.".toRegex()
        val garbage = "<.*?>".toRegex()
        val nonGroup = "[^{}]".toRegex()
        val cleanInput = list.replace(cancel, "")
        return cleanInput.replace(garbage, "").replace(nonGroup, "")
    }
}

fun main(args: Array<String>) {
    val exc8 = Exercise8("/test9.txt")
    val answerSilver = exc8.silverExercise9()
    println("The largest value after running the operations for the silver exercise is: $answerSilver")
}
