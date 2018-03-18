package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise25(fileName: String) {

    private val lines = linesAsList(fileName)
    val initialState = lines.first()[15]
    val steps = lines[1].split(" ")[5].toInt()

    val ruleMap = lines
            .filter { it.isNotBlank() }
            .drop(2)
            .map { it.split(" ").last().dropLast(1) }
            .chunked(9)
            .map {
                it[0].first() to mapOf(it[1].toInt() to Rule(it[2].toInt(), it[3], it[4].first()), it[5].toInt() to Rule(it[6].toInt(), it[7], it[8].first()))
            }.toMap()
    
    val turingMachine = TuringMachine(ruleMap, steps, initialState)

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise25(): Int {
        return turingMachine.run()
    }
}

class Rule(val value: Int, val action: String, val nextState: Char)

fun main(args: Array<String>) {
    val exc25Silver = Exercise25("/input/input25.txt")
    val answerSilver = exc25Silver.silverExercise25()
    println("The answer for the silver exercise is: $answerSilver")
}

class TuringMachine(private val ruleMap: Map<Char,Map<Int, Rule>>, private val steps: Int, private var state: Char) {
    private val tape = mutableMapOf<Int, Int>()
    private var cursor = 0

    fun run(): Int {
        repeat(steps) {
            execute()
        }
        return tape.filter { it -> it.value == 1 }.size
    }

    private fun execute() {
        val tapeValue = tape.getOrElse(cursor, { 0 })
        val rule = ruleMap[state]!![tapeValue]!!
        handleRule(rule)
    }

    private fun handleRule(rule: Rule) {
        tape.put(cursor, rule.value)
        when (rule.action) {
            "right" -> cursor++
            "left" -> cursor--
        }
        state = rule.nextState
    }
}
