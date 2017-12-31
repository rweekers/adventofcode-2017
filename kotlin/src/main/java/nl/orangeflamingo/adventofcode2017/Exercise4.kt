package nl.orangeflamingo.adventofcode2017

import io.reactivex.rxkotlin.toObservable
import java.io.InputStream

class Exercise4 {

    fun silverExercise4(file: String): Int {
        var correctPassphrases = 0

        setupLineList(file).forEach{
            if (isValidSilverPassphrase(it)) correctPassphrases++
        }
        return correctPassphrases
    }

    fun goldExercise4(file: String): Int {
        var correctPassphrases = 0

        setupLineList(file).forEach{
            if (isValidGoldPassphrase(it)) correctPassphrases++
        }
        return correctPassphrases
    }

    private fun setupLineList(file: String): List<String> {
        val inputStream: InputStream = this.javaClass.getResource(file).openStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

        return lineList
    }

    private fun isValidGoldPassphrase(line: String): Boolean {
        val words = line.split(" ")
        var validPassphrase = true

        val wordStream = words.toObservable()
        wordStream
                .filter( { word -> frequency(words, word) > 1 } )
                .subscribe( { _ -> validPassphrase = false } )

        return validPassphrase
    }

    private fun isValidSilverPassphrase(line: String): Boolean {
        val words = line.split(" ")

        val wordStream = words.toObservable()
        val distinctStream = words.toObservable().distinct()
        var originalLenght = 0
        var distinctLength = 0

        wordStream.reduce(0, {acc, _ -> acc + 1}).subscribe({a -> originalLenght = a})
        distinctStream.reduce(0, {acc, _ -> acc + 1}).subscribe({a -> distinctLength = a})

        return(originalLenght == distinctLength)
    }

    private fun isWordEqual(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        val wordList1 : List<Char> = word1.toList()
        val wordList2: MutableList<Char> = word2.toMutableList()

        wordList1.forEach({c -> if (wordList2.contains(c)) wordList2.remove(c) else return false })

        return true
    }

    private fun frequency(theList: List<String>, theObject: String): Int {
        var result = 0
        for (e in theList)
            if (isWordEqual(theObject, e))
                result++
        return result
    }
}

fun main(args: Array<String>) {
    val exc4 = Exercise4()
    val fileName = "/input/input4.txt"
    println("Total correct silver passphrases: " + exc4.silverExercise4(fileName))
    println("Total correct gold passphrases: " + exc4.goldExercise4(fileName))
}
