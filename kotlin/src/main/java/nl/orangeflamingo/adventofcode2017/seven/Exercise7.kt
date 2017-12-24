package nl.orangeflamingo.adventofcode2017.five

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise7 {

    // private val tree: Node<String> = Node()

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise7(file: String): String {
        parseInput(file)
        val list = linesAsList(file)
        val baseList = mutableListOf<String>()
        var totalString = ""
        var solution = ""
        list.toObservable().subscribe( { x ->
            baseList.add(x.substring(0, x.indexOf(" ")))
            val rest = x.substring(x.indexOf(" "))
            totalString = "$totalString$rest"
        } )
        baseList.forEach( { x -> if (totalString.indexOf(x) == -1) solution = x } )
        return solution
    }

    fun parseInput(file: String) {
        val list = linesAsList(file)
        val nodesByName = mutableMapOf<String, Node>()
        // TODO replace
        val parentChildPairs = mutableMapOf<String, String>()
        val regExp = """\w+""".toRegex()
        list.forEach( { it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val node = Node(parts[0], parts[1].toInt())
            nodesByName.put(node.name, node)

            parts.drop(2).forEach {
                parentChildPairs.put(parts[0], it)
            }
        } )

        parentChildPairs.forEach { (parentName, childName) ->
            nodesByName[parentName]!!.children.add(nodesByName[childName]!!)
        }

        println("OK")
    }
}

data class Node(val name: String, val value: Int
                , val children: MutableList<Node> = mutableListOf(), var parent: Node? = null) {

}

fun main(args: Array<String>) {
    val answerSilver = Exercise7().silverExercise7("/test7.txt")
    println("The base element for the silver exercise is: $answerSilver")
}
