package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import kotlin.math.absoluteValue

class Exercise20(fileName: String) {

    private val particles: List<Particle> = linesAsList(fileName)

    private fun linesAsList(file: String): List<Particle> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        val particles = mutableListOf<Particle>()
        lineList.forEachIndexed { index, line -> particles.add(parseParticle(index, line)) }
        return particles
    }

    private fun parseParticle(id: Int, input: String): Particle {
        return input.split("<", ">").let {
            Particle(
                    id,
                    parseToVector(it[1]),
                    parseToVector(it[3]),
                    parseToVector(it[5])
            )
        }
    }

    private fun parseToVector(input: String): Vector {
        return input.split(",").map { it.trim().toLong() }.let {
            Vector(it[0], it[1], it[2])
        }
    }

    fun silverExercise20(): Int {
        return particles.sortedBy { it.getTotalAcceleration() }.first().id
    }

    fun goldExercise20(): Int {
        return (1..500).fold(particles) { acc, _ ->
            acc.map { it.move() }
                    .groupBy { it.position }
                    .filterValues { it.size == 1 }
                    .flatMap { it.value }
        }.size
    }
}

data class Particle(val id: Int, val position: Vector, private val velocity: Vector, private val acceleration: Vector) {
    fun getTotalAcceleration(): Long {
        return acceleration.getDistance()
    }

    fun move(): Particle {
        return this.copy(
                velocity = velocity + acceleration,
                position = position + velocity + acceleration
        )
    }
}

data class Vector(private val x: Long, private val y: Long, private val z: Long) {

    fun getDistance(): Long {
        return x.absoluteValue + y.absoluteValue + z.absoluteValue
    }

    operator fun plus(that: Vector): Vector =
            Vector(x = x + that.x, y = y + that.y, z = z + that.z)
}

fun main(args: Array<String>) {
    val exc20Silver = Exercise20("/input/input20.txt")
    val answerSilver = exc20Silver.silverExercise20()
    println("The answer for the silver exercise is: $answerSilver")
    val exc20Gold = Exercise20("/input/input20.txt")
    val answerGold = exc20Gold.goldExercise20()
    println("The answer for the gold exercise is: $answerGold")
}
