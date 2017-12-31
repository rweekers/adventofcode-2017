package nl.orangeflamingo.adventofcode2017

import java.io.InputStream
import kotlin.math.absoluteValue

class Exercise7 (fileName: String) {

    private val rootNode = parseInput(fileName)

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise7(): String {
        return rootNode.name
    }

    fun goldExercise7(): Pair<String, Int> {
        return rootNode.findDifferenceInValue()
    }

    fun parseInput(file: String): Node {
        val list = linesAsList(file)
        val nodesByName = mutableMapOf<String, Node>()

        val parentChildPairs = mutableSetOf<Pair<Node, String>>()
        val regExp = """\w+""".toRegex()
        list.forEach( { it ->
            val parts = regExp.findAll(it).toList().map { it.value }
            val node = Node(parts[0], parts[1].toInt())
            nodesByName.put(node.name, node)

            parts.drop(2).forEach {
                parentChildPairs.add(Pair(node, it))
            }
        } )
        
        parentChildPairs.forEach { (parent, childName) ->
            with(nodesByName.getValue(childName)) {
                parent.children.add(this)
                this.parent = parent
            }
        }
        return nodesByName.values.firstOrNull { it.parent == null }
                ?: throw RuntimeException("Head node appears to not be there")
    }
}

data class Node(val name: String, private val value: Int
                , val children: MutableList<Node> = mutableListOf(), var parent: Node? = null) {

    private fun getTotalValue(): Int {
        var totalValue = this.value
        this.children.forEach { totalValue += it.getTotalValue() }
        return totalValue
    }

    fun findDifferenceInValue(wrongValue: Int? = null): Pair<String, Int> =
            if (wrongValue != null && childrenHaveEqualValue()) {
                Pair(name, value - wrongValue)
            } else {
                val childrenByValue = children.groupBy { it.getTotalValue() }
                val wrongValueNode = childrenByValue.minBy { it.value.size }?.value?.first()
                        ?: throw RuntimeException("No balanced children should be there")
                wrongValueNode.findDifferenceInValue(wrongValue ?: childrenByValue.keys.reduce { a, b -> a - b }.absoluteValue)
            }

    private fun childrenHaveEqualValue(): Boolean {
        return children.map { it.getTotalValue() }.distinct().size == 1
    }
}

fun main(args: Array<String>) {
    val exc7 = Exercise7("/input/input7.txt")
    val answerSilver = exc7.silverExercise7()
    println("The base element for the silver exercise is: $answerSilver")
    val answerGold = exc7.goldExercise7()
    println("The correction value for the gold exercise is ${answerGold.second} for node ${answerGold.first}")
}
