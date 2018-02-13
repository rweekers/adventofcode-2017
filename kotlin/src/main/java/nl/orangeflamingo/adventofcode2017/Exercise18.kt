package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise18(fileName: String) {

    private val instructions: List<Instruction> = parseInput(fileName)

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise18(): Int {
        return 0
    }

    private fun processInstructions() {
        return instructions.forEach{ processInstruction(it)}
    }

    private fun processInstruction(instruction: Instruction) {
        when (instruction) {
            is SetValue -> ""
        }
    }


    private fun parseInput(file: String): List<Instruction> {
        val list = linesAsList(file)
        val regExp2 = """\S+""".toRegex()

        return list.map {
            val parts = regExp2.findAll(it).toList().map { it.value }

            when (parts[0]) {
                "set" -> if (parts[2].toIntOrNull() != null)SetValue(parts[1], parts[2].toInt()) else SetRegister(parts[1], parts[2])
                "snd" -> Sound(parts[1])
                "add" -> if (parts[2].toIntOrNull() != null)AddValue(parts[1], parts[2].toInt()) else AddRegister(parts[1], parts[2])
                "mul" -> if (parts[2].toIntOrNull() != null)MultiplyValue(parts[1], parts[2].toInt()) else MultiplyRegister(parts[1], parts[2])
                "mod" -> if (parts[2].toIntOrNull() != null)ModuloValue(parts[1], parts[2].toInt()) else ModuloRegister(parts[1], parts[2])
                "rec" -> Recover(parts[1])
                "jgz" -> if (parts[2].toIntOrNull() != null)JumpValue(parts[1], parts[2].toInt()) else JumpRegister(parts[1], parts[2])
                else -> Sound(parts[1])
            }
        }
    }
}

sealed class Instruction

class SetValue(val register: String, val value: Int) : Instruction()

class SetRegister(val register: String, val value: String) : Instruction()

class Sound(val register: String) : Instruction()

class AddValue(val register: String, val value: Int) : Instruction()

class AddRegister(val register: String, val value: String) : Instruction()

class MultiplyValue(val register: String, val multiplier: Int) : Instruction()

class MultiplyRegister(val register: String, val multiplier: String) : Instruction()

class ModuloValue(val register: String, val divider: Int) : Instruction()

class ModuloRegister(val register: String, val divider: String) : Instruction()

class Recover(val register: String) : Instruction()

class JumpValue(val register: String, val offset: Int) : Instruction()

class JumpRegister(val register: String, val offset: String) : Instruction()

fun main(args: Array<String>) {
    val exc18 = Exercise18("/input/input18.txt")
    val answerSilver = exc18.silverExercise18()
    println("The answer for the silver exercise is: $answerSilver")
}
