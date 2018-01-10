package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise12(fileName: String) {

    private val steps = parseInput(fileName)

    fun silverExercise12(): Int {
        // steps.forEach({println(it)})
        return 0
    }

    private fun parseInput(file: String): List<String> {
        val list = linesAsList(file)
        val regExp = """\w+""".toRegex()
        val programsByName = mutableMapOf<Int, Program>()
        list.forEach( { it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val masterProgram = programsByName.getOrDefault(parts[0].toInt(), Program(parts[0].toInt()))
            programsByName.put(parts[0].toInt(), masterProgram)
            println("For ${parts[0]} the linked stuff is: ")
            parts.drop(1).forEach( {
                val program = programsByName.getOrDefault(it.toInt(), Program(it.toInt()))
                program.addConnection(masterProgram)
                masterProgram.addConnection(program)
                programsByName.put(it.toInt(), program)
                println(it)
            })
        })
        val p = programsByName.getOrElse(0, { Program(0) })
        p.getTotalConnections(p)
        return list
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }
}

data class Program(val name: Int) {

    private val connections = mutableSetOf<Program>()

    fun addConnection(program: Program) {
        connections.add(program)
    }

    fun getTotalConnections(program: Program): Int {
        val visited = mutableListOf<Program>()
        val totalConnections = mutableSetOf<Program>()
        program.connections.forEach({
            if (!visited.contains(it)) {
                totalConnections.add(program)
                visited.add(it)
                getTotalConnections(it)
            }
        })
        return totalConnections.size
    }
}

fun main(args: Array<String>) {
    val answerSilver = Exercise12("/input/input12.txt").silverExercise12()
    println("The answer for the silver exercise is: $answerSilver")
}
