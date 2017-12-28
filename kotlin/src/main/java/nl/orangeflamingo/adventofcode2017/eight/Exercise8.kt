package nl.orangeflamingo.adventofcode2017.eight

import java.io.InputStream

class Exercise8 (fileName: String) {

    private val register = HashMap<String, Int>()

    init {
        parseInput(fileName)
    }

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise8(): String {
        return register.toString()
    }

    fun parseInput(file: String): Map<String, Int> {
        val list = linesAsList(file)

        val regExp = """\S+""".toRegex()
        list.forEach( { it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val element = parts[0]
            val addition = giveAddition(parts[1], parts[2].toInt())
            val predicate = parts[4] + parts[5] + parts[6]
            println("${it} is divided into element ${element} with addition ${addition} and predicate ${predicate}")
        } )
        return register
    }

    private fun updateRegisterValue(reg: String, predicate: String, addition: Int) {
        //
    }

    private fun giveAddition(action: String, amount: Int): Int {
        return if (action == "inc") amount else - amount
    }
}

fun main(args: Array<String>) {
    val exc8 = Exercise8("/test8.txt")
    val answerSilver = exc8.silverExercise8()
    println("The base element for the silver exercise is: $answerSilver")
}
