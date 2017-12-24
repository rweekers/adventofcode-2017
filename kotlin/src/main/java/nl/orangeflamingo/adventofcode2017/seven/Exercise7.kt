package nl.orangeflamingo.adventofcode2017.seven

import java.io.InputStream

class Exercise7 {

    private fun linesAsList(file: String): MutableList<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    fun silverExercise7(file: String): String {
        return parseInput(file)
    }

    fun parseInput(file: String): String {
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

        val rootNode = nodesByName.values.firstOrNull { it.parent == null }
                ?: throw RuntimeException("Head node appears to not be there")

        return rootNode.name
    }
}

data class Node(val name: String, val value: Int
                , val children: MutableList<Node> = mutableListOf(), var parent: Node? = null) {

    override fun toString(): String {
        return this.name
    }
}

fun main(args: Array<String>) {
    val answerSilver = Exercise7().silverExercise7("/input7.txt")
    println("The base element for the silver exercise is: $answerSilver")
}
