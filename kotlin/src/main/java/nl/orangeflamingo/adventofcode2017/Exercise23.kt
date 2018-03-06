package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise23(fileName: String) {

    private val instructions: List<String> = linesAsList(fileName)

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise23(): Int {
        val coprocessor = Coprocessor(instructions)
        coprocessor.processInstructions()
        return coprocessor.calledMul
    }

    fun goldExercise23(): Int {
        val a = instructions.first().split(" ")[2].toInt() * 100 + 100000
        return (a..a + 17000 step 17).count {
            !it.toBigInteger().isProbablePrime(5)
        }
    }
}

data class Coprocessor(private val instructions: List<String>, private val register: MutableMap<String, Long> = mutableMapOf(), private var index: Int = 0, var calledMul: Int = 0) {

    fun processInstructions(): Coprocessor {
        do {
            instructions.getOrNull(index)?.let {
                processInstruction(it)
            }
        } while (index in 0 until instructions.size)
        return this
    }

    private fun getRegisterValue(registerKey: String): Long {
        return register.getOrElse(registerKey) {
            try {
                registerKey.toLong()
            } catch (e: NumberFormatException) {
                0
            }
        }
    }

    private fun processInstruction(instruction: String) {
        val parts = instruction.split(" ")

        when (parts[0]) {
            "set" -> register[parts[1]] = getRegisterValue(parts[2])
            "sub" -> register[parts[1]] = getRegisterValue(parts[1]) - getRegisterValue(parts[2])
            "mul" -> {
                register[parts[1]] = getRegisterValue(parts[1]) * getRegisterValue(parts[2])
                calledMul++
            }
            "jnz" -> if (getRegisterValue(parts[1]) != 0L) {
                index += getRegisterValue(parts[2]).toInt().dec()
            }
        }
        index++
    }
}

fun main(args: Array<String>) {
    val exc23Silver = Exercise23("/input/input23.txt")
    val answerSilver = exc23Silver.silverExercise23()
    println("The answer for the silver exercise is: $answerSilver")
    val exc23Gold = Exercise23("/input/input23.txt")
    val answerGold = exc23Gold.goldExercise23()
    println("The answer for the gold exercise is: $answerGold")
}
