package day1

import java.io.File
import java.net.URI

internal class Part2 {

    val numbers = mutableSetOf<Int>()

    fun run(): Int {
        readNumbersFromFile()
        return process()
    }

    private fun readNumbersFromFile() {
        val fileUrl = javaClass.getResource("input.txt")
        val fileUri = URI(fileUrl.toString())
        File(fileUri).useLines {
            it.forEach {number -> numbers.add(number.toInt()) }
        }
    }

    private fun process(): Int {
        numbers.forEach { i ->
            val remaining = 2020 - i
            numbers.forEach { j ->
                val matchingNumber = remaining - j
                if (numbers.contains(matchingNumber)) {
                    return matchingNumber * i * j
                }
            }
        }
        return 0
    }
}