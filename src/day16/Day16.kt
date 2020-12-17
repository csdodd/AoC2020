package day16

import loadFileToStringArray
import printAsHeader

class Day16 {

    fun run() {
        "Day16".printAsHeader()
        //testPart1()
        //testPart2()
        realData()
    }

    private fun testPart1() {
        val input = loadFileToStringArray("/day16/example.txt")
        val ticketRules = TicketInformation(input)
        println("Test result is ${ticketRules.findSumOfInvalidFields()}")
    }

    private fun testPart2() {
        val input = loadFileToStringArray("/day16/example2.txt")
        val ticketRules = TicketInformation(input)
        ticketRules.processTicketsAgainstRules()
    }

    private fun realData() {
        val input = loadFileToStringArray("/day16/input.txt")
        val ticketRules = TicketInformation(input)
        println("Real result is ${ticketRules.findSumOfInvalidFields()}")
        val part2 = ticketRules.processTicketsAgainstRules()
        println("Real sum of departure fields  is $part2")
    }
}