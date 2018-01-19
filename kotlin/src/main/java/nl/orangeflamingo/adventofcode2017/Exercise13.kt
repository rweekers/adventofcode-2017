package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise13(fileName: String) {

    private val firewallMap = mutableMapOf<Int, Firewall>()
    private val fileName = fileName

    fun silverExercise13(): Int {
        return calculateScore()
    }

    fun goldExercise13(): Int {
        var delay = 0
        var score = calculateScore()
        println("Score for delay $delay is $score")
        while (score != 0) {
            delay++
            score = calculateScore(delay)
            println("Score for delay $delay is $score")
        }
        return delay
    }

    private fun calculateScore(delay: Int = 0): Int {
        parseInput(fileName)
        var iter = 0
        while (iter < delay) {
            doOneTick()
            iter++
        }
        var i = 0
        var score = 0
        while (i <= getMaxValue()) {
            var currFirewall = getCurrFirewall(i)
            if (currFirewall.index == 0) {
                score += currFirewall.range * i
            }
            doOneTick()
            i++
        }
        return score
    }

    private fun doOneTick() {
        firewallMap.forEach { _, u -> u.doMove() }
    }

    private fun getCurrFirewall(index: Int): Firewall {
        return firewallMap.getOrDefault(index, Firewall(0))
    }

    private fun parseInput(file: String) {
        firewallMap.clear()
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

    private fun getMaxValue(): Int {
        return firewallMap.keys.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow { RuntimeException("Map is empty!") }
    }
}

data class Firewall(val range: Int, var index: Int = 0) {
    var inc = true

    fun doMove() {
        index = calculateIndex()
    }

    private fun calculateIndex(): Int {
        return if (inc) {
            if (index + 1 >= range) {
                inc = false
                index - 1
            } else {
                index + 1
            }
        } else {
            if (index <= 0) {
                inc = true
                index + 1
            } else {
                index - 1
            }
        }
    }
}

fun main(args: Array<String>) {
    val exc13 = Exercise13("/input/input13.txt")
    val answerSilver = exc13.silverExercise13()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc13.goldExercise13()
    println("The answer for the gold exercise is: $answerGold")
}
