package nl.orangeflamingo.adventofcode2017

import java.io.InputStream

class Exercise8 (fileName: String) {

    private val register = HashMap<String, Int>()
    private var maxValue = 0

    init {
        parseInput(fileName)
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise8(): Int {
        return register.maxBy { it.value }!!.value
    }

    fun goldExercise8(): Int {
        return maxValue
    }

    private fun parseInput(file: String): Map<String, Int> {
        val list = linesAsList(file)

        val regExp = """\S+""".toRegex()
        list.forEach( { it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val addition = giveAddition(parts[1], parts[2].toInt())
            val predicate = processPredicate(getValue(parts[4]), parts[5], parts[6].toInt())
            if (predicate) register[parts[0]] = getValue(parts[0]) + addition
            maxValue = if (!register.isEmpty() && register.maxBy { it.value }!!.value > maxValue) register.maxBy { it.value }!!.value else maxValue
        } )
        return register
    }

    private fun getValue(key: String): Int {
        return if (register.containsKey(key)) register.getValue(key) else 0
    }

    private fun processPredicate(value: Int, comparisonOperator: String, compareValue: Int): Boolean {
        var answer = false
        when (comparisonOperator) {
            ">" -> answer = value > compareValue
            ">=" -> answer = value >= compareValue
            "<" -> answer = value < compareValue
            "<=" -> answer = value <= compareValue
            "==" -> answer = value == compareValue
            "!=" -> answer = value != compareValue
            else -> { // Note the block
                RuntimeException("Unexpected comparison operator")
            }
        }
        return answer
    }

    private fun giveAddition(action: String, amount: Int): Int {
        return if (action == "inc") amount else - amount
    }
}

fun main(args: Array<String>) {
    val exc8 = Exercise8("/input/input8.txt")
    val answerSilver = exc8.silverExercise8()
    println("The largest value after running the operations for the silver exercise is: $answerSilver")
    val answerGold = exc8.goldExercise8()
    println("The largest value found after running the operations for the gold exercise is: $answerGold")
}
