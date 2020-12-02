package day2

import java.io.File
import java.net.URI

class Day2 {

    fun part1() {
        val entries = loadFromFile()
        val oldPolicyCount = processEntriesWithOldPolicy(entries)
        val newPolicyCount = processEntriesWithNewPolicy(entries)
        println("Valid passwords according to old policy is: $oldPolicyCount")
        println("Valid passwords according to new policy is: $newPolicyCount")
    }

    private fun loadFromFile(): ArrayList<String> {
        val entries = arrayListOf<String>()
        val fileUrl = Day2::class.java.getResource("entries.txt")
        val fileUri = URI(fileUrl.toString())
        File(fileUri).readLines().forEach {
            entries.add(it)
        }
        return entries
    }

    private fun processEntriesWithOldPolicy(entries: ArrayList<String>): Int {
        return entries
            .map { parseEntry(it) }
            .map { it.isValidAccordingToOldPolicy() }
            .filter { it }
            .count()
    }

    private fun processEntriesWithNewPolicy(entries: ArrayList<String>): Int {
        return entries
            .map { parseEntry(it) }
            .map { it.isValidAccordingToNewPolicy() }
            .filter { it }
            .count()
    }

    private fun parseEntry(entry: String): Entry {
        val parts = entry.split(" ")

        val occurrences = parts[0].split("-")
        val minimumOccurrences = occurrences[0].toInt()
        val maximumOccurrences = occurrences[1].toInt()
        val letter = parts[1].first()
        val password = parts[2]
        return Entry(minimumOccurrences, maximumOccurrences, letter, password)
    }

    fun test() {
        val testData = arrayListOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc")

        processEntriesWithOldPolicy(testData)
    }
}