package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise12(fileName: String) {

    private val totalConnections = mutableSetOf<Program>()
    private val steps = parseInput(fileName)

    private var groups = 0
    private val inAnyGroup = mutableSetOf<Program>()

    fun silverExercise12(): Int {
        return steps.size
    }

    private fun parseInput(file: String): Set<Program> {
        val list = linesAsList(file)
        val regExp = """\w+""".toRegex()
        val programsByName = mutableMapOf<Int, Program>()
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
        val p = programsByName.getOrElse(0, { Program(0) })
        calculateTotalConnections(p, mutableSetOf<Program>())
        return totalConnections
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    private fun calculateTotalConnections(program: Program, visited: MutableSet<Program>) {
        program.connections.forEach({
            if (!visited.contains(it)) {
                totalConnections.add(it)
                visited.add(it)
                calculateTotalConnections(it, visited)
            }
        })
    }

    fun calculateTotalGroups(program: Program) {
        program.connections.forEach({
            if (!inAnyGroup.contains(program)) {
                groups++
                // create groups
            }
        })
    }
}

data class Program(val name: Int) {

    val connections = mutableSetOf<Program>()

    fun addConnection(program: Program) {
        connections.add(program)
    }
}

fun main(args: Array<String>) {
    val answerSilver = Exercise12("/input/input12.txt").silverExercise12()
    println("The answer for the silver exercise is: $answerSilver")
}
