package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.TimeUnit

class Exercise18(fileName: String) {

    private val instructions: List<Instruction> = parseInput(fileName)

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

        RealDuet(instructions, program1Ingoing, program0Ingoing).processInstructions()

        return RealDuet(instructions, program1Ingoing, program0Ingoing).processInstructions().get()
    }

    private fun parseInput(file: String): List<Instruction> {
        val list = linesAsList(file)
        val regExp2 = """\S+""".toRegex()

        return list.map {
            val parts = regExp2.findAll(it).toList().map { it.value }

            when (parts[0]) {
                "set" -> if (parts[2].toLongOrNull() != null) SetValue(parts[1].toFirstChar(), parts[2].toLong()) else SetRegister(parts[1].toFirstChar(), parts[2].toFirstChar())
                "snd" -> Sound(parts[1].toFirstChar())
                "add" -> if (parts[2].toLongOrNull() != null) AddValue(parts[1].toFirstChar(), parts[2].toLong()) else AddRegister(parts[1].toFirstChar(), parts[2].toFirstChar())
                "mul" -> if (parts[2].toLongOrNull() != null) MultiplyValue(parts[1].toFirstChar(), parts[2].toLong()) else MultiplyRegister(parts[1].toFirstChar(), parts[2].toFirstChar())
                "mod" -> if (parts[2].toLongOrNull() != null) ModuloValue(parts[1].toFirstChar(), parts[2].toLong()) else ModuloRegister(parts[1].toFirstChar(), parts[2].toFirstChar())
                "rcv" -> Recover(parts[1].toFirstChar())
                "jgz" -> if (parts[2].toLongOrNull() != null) JumpValue(parts[1].toFirstChar(), parts[2].toLong()) else JumpRegister(parts[1].toFirstChar(), parts[2].toFirstChar())
                else -> Sound(parts[1].toFirstChar())
            }
        }
    }

    private fun String.toFirstChar(): Char {
        return this.toCharArray()[0]
    }
}

data class Duet(private val instructions: List<Instruction>, private val register: MutableMap<Char, Long> = mutableMapOf(), private var index: Int = 0, private var recoveredFrequency: Long = 0, var foundFrequency: Long = 0) {
    fun processInstructions() {
        while (recoveredFrequency == 0L) {
            processInstruction(instructions[index])
        }
    }

    private fun setRegisterValue(registerKey: Char, value: Long) {
        register[registerKey] = value
    }

    private fun getRegisterValue(registerKey: Char): Long {
        return register.computeIfAbsent(registerKey, { _ -> 0 })
    }

    private fun processInstruction(instruction: Instruction) {
        when (instruction) {
            is SetValue -> {
                setRegisterValue(instruction.register, instruction.value)
                index++
            }
            is SetRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.value))
                index++
            }
            is Sound -> {
                foundFrequency = getRegisterValue(instruction.register)
                index++
            }
            is AddValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) + instruction.value)
                index++
            }
            is AddRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) + getRegisterValue(instruction.value))
                index++
            }
            is MultiplyValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) * instruction.multiplier)
                index++
            }
            is MultiplyRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) * getRegisterValue(instruction.multiplier))
                index++
            }
            is ModuloValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) % instruction.divider)
                index++
            }
            is ModuloRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) % getRegisterValue(instruction.divider))
                index++
            }
            is Recover -> {
                if (getRegisterValue(instruction.register) > 0) recoveredFrequency = getRegisterValue(instruction.register)
                index++
            }
            is JumpValue -> if (getRegisterValue(instruction.register) > 0) index += instruction.offset.toInt() else index++
            is JumpRegister -> if (getRegisterValue(instruction.register) > 0) index += getRegisterValue(instruction.offset).toInt() else index++
        }
    }
}

data class RealDuet(private val instructions: List<Instruction>,
                    private val outgoing: BlockingQueue<Long>,
                    private val incoming: BlockingQueue<Long>,
                    private val register: MutableMap<Char, Long> = mutableMapOf(),
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

    private fun setRegisterValue(registerKey: Char, value: Long) {
        register[registerKey] = value
    }

    private fun getRegisterValue(registerKey: Char): Long {
        return register.computeIfAbsent(registerKey, { _ -> 0 })
    }

    private fun processInstruction(instruction: Instruction) {
        when (instruction) {
            is SetValue -> {
                setRegisterValue(instruction.register, instruction.value)
                index++
            }
            is SetRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.value))
                index++
            }
            is Sound -> {
                index++
                outgoing.put(getRegisterValue(instruction.register))
                timesSent += 1
            }
            is AddValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) + instruction.value)
                index++
            }
            is AddRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) + getRegisterValue(instruction.value))
                index++
            }
            is MultiplyValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) * instruction.multiplier)
                index++
            }
            is MultiplyRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) * getRegisterValue(instruction.multiplier))
                index++
            }
            is ModuloValue -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) % instruction.divider)
                index++
            }
            is ModuloRegister -> {
                setRegisterValue(instruction.register, getRegisterValue(instruction.register) % getRegisterValue(instruction.divider))
                index++
            }
            is Recover -> {
                try {
                    register[instruction.register] = incoming.poll(1, TimeUnit.SECONDS)
                } catch (e: Exception) {
                    index = -2
                }
            }
            is JumpValue -> if (getRegisterValue(instruction.register) > 0) index += instruction.offset.toInt() else index++
            is JumpRegister -> if (getRegisterValue(instruction.register) > 0) index += getRegisterValue(instruction.offset).toInt() else index++
        }
    }
}

sealed class Instruction

class SetValue(val register: Char, val value: Long) : Instruction()

class SetRegister(val register: Char, val value: Char) : Instruction()

class Sound(val register: Char) : Instruction()

class AddValue(val register: Char, val value: Long) : Instruction()

class AddRegister(val register: Char, val value: Char) : Instruction()

class MultiplyValue(val register: Char, val multiplier: Long) : Instruction()

class MultiplyRegister(val register: Char, val multiplier: Char) : Instruction()

class ModuloValue(val register: Char, val divider: Long) : Instruction()

class ModuloRegister(val register: Char, val divider: Char) : Instruction()

class Recover(val register: Char) : Instruction()

class JumpValue(val register: Char, val offset: Long) : Instruction()

class JumpRegister(val register: Char, val offset: Char) : Instruction()

fun main(args: Array<String>) {
//    val exc18Silver = Exercise18("/input/input18.txt")
//    val answerSilver = exc18Silver.silverExercise18()
//    println("The answer for the silver exercise is: $answerSilver")
    val exc18Gold = Exercise18("/input/input18.txt")
    val answerGold = exc18Gold.goldExercise18()
    println("The answer for the gold exercise is: $answerGold")
}
