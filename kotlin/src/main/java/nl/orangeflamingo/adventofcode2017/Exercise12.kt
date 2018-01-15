package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise12(fileName: String) {

    private val programsByName = mutableMapOf<Int, Program>()
    private var groups = 0
    private val inAnyGroup = mutableSetOf<Program>()

    init {
        parseInput(fileName)
    }

    fun silverExercise12(): Int {
        return getConnectedTo(programsByName.getOrDefault(0, Program(0))).size
    }

    fun goldExercise12(): Int {
        return calculateTotalGroups(programsByName)
    }

    private fun parseInput(file: String) {
        val list = linesAsList(file)
        val regExp = """\w+""".toRegex()
        list.forEach({ it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val masterProgram = programsByName.getOrDefault(parts[0].toInt(), Program(parts[0].toInt()))
            programsByName.put(parts[0].toInt(), masterProgram)
            parts.drop(1).forEach({
                val program = programsByName.getOrDefault(it.toInt(), Program(it.toInt()))
                program.addConnection(masterProgram)
                masterProgram.addConnection(program)
                programsByName.put(it.toInt(), program)
            })
        })
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun getConnectedTo(program: Program, visited: MutableSet<Program> = mutableSetOf()): Set<Program> {
        program.connections.forEach({
            if (!visited.contains(it)) {
                visited.add(it)
                getConnectedTo(it, visited)
            }
        })
        return visited
    }

    private fun calculateTotalGroups(totalList: Map<Int, Program>, visited: MutableSet<Program> = mutableSetOf()): Int {
        totalList.values.forEach({
            if (!visited.contains(it)) {
                if(!inAnyGroup.contains(it)) {
                    groups++
                    inAnyGroup.addAll(getConnectedTo(it))
                }
            }
        })
        return groups
    }
}

data class Program(val name: Int) {

    val connections = mutableSetOf<Program>()

    fun addConnection(program: Program) {
        connections.add(program)
    }
}

fun main(args: Array<String>) {
    val exc12 = Exercise12("/input/input12.txt")
    val answerSilver = exc12.silverExercise12()
    println("The answer for the silver exercise is: $answerSilver")
    val answerGold = exc12.goldExercise12()
    println("The answer for the silver exercise is: $answerGold")
}
