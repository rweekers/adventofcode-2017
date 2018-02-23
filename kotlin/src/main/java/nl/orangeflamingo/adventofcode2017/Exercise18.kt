package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class Exercise18(fileName: String) {

    private val instructions: List<String> = linesAsList(fileName)

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise18(): Long {
        val duet = Duet(instructions)
        duet.processInstructions()
        return duet.foundFrequency
    }

    fun goldExercise18(): Long {
        val program0Ingoing = LinkedBlockingDeque<Long>()
        val program1Ingoing = LinkedBlockingDeque<Long>()

        RealDuet(instructions, program1Ingoing, program0Ingoing, mutableMapOf("p" to 0L)).processInstructions()

        return RealDuet(instructions, program0Ingoing, program1Ingoing, mutableMapOf("p" to 1L)).processInstructions().get()
    }
}

data class Duet(private val instructions: List<String>, private val register: MutableMap<String, Long> = mutableMapOf(), private var index: Int = 0, private var recoveredFrequency: Long = 0, var foundFrequency: Long = 0) {

    fun processInstructions() {
        while (recoveredFrequency == 0L) {
            processInstruction(instructions[index])
        }
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
            "snd" -> foundFrequency = getRegisterValue(parts[1])
            "set" -> register[parts[1]] = getRegisterValue(parts[2])
            "add" -> register[parts[1]] = getRegisterValue(parts[1]) + getRegisterValue(parts[2])
            "mul" -> register[parts[1]] = getRegisterValue(parts[1]) * getRegisterValue(parts[2])
            "mod" -> register[parts[1]] = getRegisterValue(parts[1]) % getRegisterValue(parts[2])
            "rcv" -> if (getRegisterValue(parts[1]) != 0L) {
                recoveredFrequency = foundFrequency
            }
            "jgz" -> if (getRegisterValue(parts[1]) > 0L) {
                index += getRegisterValue(parts[2]).toInt().dec()
            }
        }
        index++
    }
}

data class RealDuet(private val instructions: List<String> = mutableListOf(),
                    private val incoming: BlockingQueue<Long>,
                    private val outgoing: BlockingQueue<Long>,
                    private val register: MutableMap<String, Long> = mutableMapOf(),
                    private var index: Int = 0,
                    private var timesSent: Long = 0) {

    fun processInstructions(): CompletableFuture<Long> =
            CompletableFuture.supplyAsync {
                do {
                    instructions.getOrNull(index)?.let {
                        processInstruction(it)
                    }
                } while (index in 0 until instructions.size)
                timesSent
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
            "snd" -> {
                outgoing.put(getRegisterValue(parts[1]))
                timesSent += 1
            }
            "set" -> register[parts[1]] = getRegisterValue(parts[2])
            "add" -> register[parts[1]] = getRegisterValue(parts[1]) + getRegisterValue(parts[2])
            "mul" -> register[parts[1]] = getRegisterValue(parts[1]) * getRegisterValue(parts[2])
            "mod" -> register[parts[1]] = getRegisterValue(parts[1]) % getRegisterValue(parts[2])
            "rcv" ->
                try {
                    register[parts[1]] = incoming.poll(1, TimeUnit.SECONDS)
                } catch (e: Exception) {
                    index = -2
                }
            "jgz" ->
                if (getRegisterValue(parts[1]) > 0L) {
                    index += getRegisterValue(parts[2]).toInt().dec()
                }
            else -> throw IllegalArgumentException("No such instruction ${parts[0]}")
        }
        index += 1
    }
}

fun main(args: Array<String>) {
    val exc18Silver = Exercise18("/input/input18.txt")
    val answerSilver = exc18Silver.silverExercise18()
    println("The answer for the silver exercise is: $answerSilver")
    val exc18Gold = Exercise18("/input/input18.txt")
    val answerGold = exc18Gold.goldExercise18()
    println("The answer for the gold exercise is: $answerGold")
}
