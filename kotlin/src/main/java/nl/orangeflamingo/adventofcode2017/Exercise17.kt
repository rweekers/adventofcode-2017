package nl.orangeflamingo.adventofcode2017

class Exercise17(private val stepsForward: Int) {

    val spinlock = SpinLock(stepsForward)

    fun silverExercise17(): Int {
        (1..2017).forEach { spinlock.spin(it) }
        return spinlock.getNextValue(2017)
    }

    fun goldExercise17(): Int {
        var currIndex = 0
        var valueOne = 0
        (1..50_000_000).forEach {
            currIndex = ((currIndex + stepsForward) % it) + 1
            if (currIndex == 1) valueOne = it
        }
        return valueOne
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

    fun getNextValue(targetValue: Int): Int {
        return circularBuffer[circularBuffer.indexOf(targetValue) + 1]
    }

}

fun main(args: Array<String>) {
    val stepsForward = 314
    val exc17Silver = Exercise17(stepsForward)
    val answerSilver = exc17Silver.silverExercise17()
    println("The answer for the silver exercise is: $answerSilver")
    val exc17Gold = Exercise17(stepsForward)
    val answerGold = exc17Gold.goldExercise17()
    println("The answer for the gold exercise is: $answerGold")
}
