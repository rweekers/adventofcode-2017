package nl.orangeflamingo.adventofcode2017.five

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise5 {

    fun silverExercise5(file: String): Int {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

        lineList.toObservable().subscribe( { x -> println(x)} )
        return 0
    }
}

fun main(args: Array<String>) {
    val exc5 = Exercise5()
    println("Total correct silver number of steps: " + exc5.silverExercise5("/input5.txt"))
}
