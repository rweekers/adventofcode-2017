package nl.orangeflamingo.adventofcode2017

import io.reactivex.Observable
import java.io.InputStream

class Exercise13(fileName: String) {

    private val firewallMap = mutableMapOf<Int, Firewall>()

    init {
        parseInput(fileName)
    }

    fun silverExercise13(): Int {
        var i = 0
        var score = 0
        while (i <= getMaxValue()) {
            var currFirewall = getCurrFirewall(i)
            if (currFirewall.index == 0) { score += currFirewall.range * i }
            firewallMap.forEach { _, u -> u.doMove() }
            i++
        }
        return 0
    }

    private fun getCurrFirewall(index: Int): Firewall {
        return firewallMap.getOrDefault(index, Firewall(0))
    }

    private fun parseInput(file: String) {
        val list = linesAsList(file)
        val regExp = """\w+""".toRegex()
        list.forEach({ it ->
            val parts = regExp.findAll(it).toList().map { it.value.toInt() }
            firewallMap[parts[0]] = Firewall(parts[1])
        })
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun getIterableStream(): Observable<Int> {
        return Observable.range(0, getMaxValue() + 1)
    }

    private fun getMaxValue(): Int {
        return firewallMap.keys.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow { RuntimeException("Map is empty!") }
    }
}

data class Firewall(val range: Int, var index: Int = 0) {
    fun doMove() {
        index = calculateIndex()
    }

    private fun calculateIndex(): Int {
        return if (index + 1 >= range) 0 else index + 1
    }
}

fun main(args: Array<String>) {
    val answerSilver = Exercise13("/input/input13.txt").silverExercise13()
    println("The answer for the silver exercise is: $answerSilver")
}
