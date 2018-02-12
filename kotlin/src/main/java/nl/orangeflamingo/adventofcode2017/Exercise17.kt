package nl.orangeflamingo.adventofcode2017

class Exercise17(stepsForward: Int) {

    val spinlock = SpinLock(stepsForward)

    fun silverExercise17(): Int {
        return processMoves()
    }

    private fun processMoves(): Int {
        (1..2017).forEach { spinlock.spin(it) }
        return spinlock.getNextValue()
    }
}

class SpinLock(private val stepsForward: Int, private val circularBuffer: MutableList<Int> = mutableListOf()) {

    private var index = 0

    init {
        circularBuffer.add(0)
    }

    fun spin(value: Int) {
        index = getCircularIndex(index + stepsForward)
        if (index == circularBuffer.size - 1) {
            circularBuffer.add(value)
            index = circularBuffer.size - 1
        } else {
            index += 1
            circularBuffer.add(index, value)
        }
    }

    private fun getCircularIndex(newIndex: Int): Int {
        return (newIndex % circularBuffer.size )
    }

    fun getNextValue(): Int {
        return circularBuffer.get(circularBuffer.indexOf(2017) + 1)
    }

}

fun main(args: Array<String>) {
    val stepsForward = 314
    val exc17 = Exercise17(stepsForward)
    val answerSilver = exc17.silverExercise17()
    println("The answer for the silver exercise is: $answerSilver")
}
