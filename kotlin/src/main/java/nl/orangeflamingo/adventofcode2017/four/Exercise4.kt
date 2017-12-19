package nl.orangeflamingo.adventofcode2017.four

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise4() {

    fun exercise4(file: String): Int {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream();
        val lineList = mutableListOf<String>()
        var correctPassphrases = 0

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
        lineList.forEach{
            if (isValidPassphrase(it)) correctPassphrases++
        }
        return correctPassphrases
    }

    private fun isValidPassphrase(line: String): Boolean {
        val words = line.split(" ")

        val wordStream = words.toObservable()

        // TODO
        val distinctGoldWordStream = wordStream;

        val distinctWordStream = words.toObservable().distinct()
        var originalLenght = 0
        var distinctLength = 0

        wordStream.reduce(0, {acc, _ -> acc + 1}).subscribe({a -> originalLenght = a})
        distinctWordStream.reduce(0, {acc, _ -> acc + 1}).subscribe({a -> distinctLength = a})

        return(originalLenght == distinctLength)
    }

    private fun isWordEqual(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        var wordList1 : List<Char> = word1.toList()
        var wordList2: MutableList<Char> = word2.toMutableList()

        wordList1.forEach({c -> if (wordList2.contains(c)) wordList2.remove(c) else return false })

        return true;
    }
}

fun main(args: Array<String>) {
    val exc4 = Exercise4()
    println("Total correct passphrases: " + exc4.exercise4("/input.txt"))
}
