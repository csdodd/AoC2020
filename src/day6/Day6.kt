package day6

import loadFileToStringArray
import printAsHeader
import java.util.ArrayList

class Day6 {

    fun run() {
        "Day6".printAsHeader()
        println("Test result is: ${test()}")
        realData()
    }

    private fun realData() {
        val input = loadFileToStringArray("/day6/input.txt")
        val groups = generateGroups(input)
        val uniqueCount = groups.sumBy { it.getUniqueCount() }
        val commonCount = groups.sumBy { it.getCommonCount() }

        println("Count for groups where anyone answered yes to a question: $uniqueCount")
        println("Count for groups where everyone answered yes to a question: $commonCount")
    }

    private fun test(): Int {
        val input = loadFileToStringArray("/day6/example.txt")
        val groups = generateGroups(input)
        return groups.sumBy { it.getUniqueCount() }
    }

    private fun generateGroups(input: ArrayList<String>): List<Group> {
        val groups = arrayListOf<Group>()
        var group = Group()

        input.forEach {
            if (it.isEmpty()) {
                groups.add(group)
                group = Group()
            }
            else {
                val person = Person(it)
                group.people.add(person)
            }
        }
        groups.add(group)

        return groups
    }
}