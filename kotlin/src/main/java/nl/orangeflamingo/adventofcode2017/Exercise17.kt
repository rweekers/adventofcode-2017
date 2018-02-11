package nl.orangeflamingo.adventofcode2017

class Exercise17(stepsForward: Int) {

    val spinlock = SpinLock(stepsForward)

    fun silverExercise16(): Int {
        return processMoves()
    }

    private fun processMoves(): Int {
        (1..2017).forEach { spinlock.spin(it) }
        return 0
    }
}

class SpinLock(val stepsForward: Int, private val circularBuffer: MutableList<Int> = mutableListOf()) {

    private var index = 0

    init {
        circularBuffer.add(0)
    }

    fun spin(value: Int) {
        println("spinning...")
        index = getCircularIndex(index + stepsForward)
        circularBuffer.add(index, value)
    }

    private fun getCircularIndex(newIndex: Int): Int {
        return (circularBuffer.size % newIndex) - 1
    }

}

fun main(args: Array<String>) {
    val stepsForward = 3
    val exc17 = Exercise17(stepsForward)
    val answerSilver = exc17.silverExercise16()
    println("The answer for the silver exercise is: $answerSilver")
}
