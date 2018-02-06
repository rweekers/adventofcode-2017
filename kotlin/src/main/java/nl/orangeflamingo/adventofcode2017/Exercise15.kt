package nl.orangeflamingo.adventofcode2017

class Exercise15() {

    private val factorA = 16807
    private val factorB = 48271
    private val divider = 2147483647

    fun silverExercise15(): Int {
        return dowork()
    }

    private fun dowork(): Int {
        var b: Long = 65
        (1..5).forEach {
            val generator = Generator(b, factorA, divider).calculate()
            b = generator.value
            println(b)
        }
        return 0
    }
}

data class Generator(val value: Long, private val multiplyFactor: Int, private val divider: Int) {

    fun calculate(): Generator {
        val answer: Long = value.times(multiplyFactor).rem(2147483647)
        return Generator(answer, multiplyFactor, divider)
    }
}

fun main(args: Array<String>) {
    val exc15 = Exercise15()
    val answerSilver = exc15.silverExercise15()
    println("The answer for the silver exercise is: $answerSilver")
}
