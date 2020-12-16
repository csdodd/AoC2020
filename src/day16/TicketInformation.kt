package day16

class TicketInformation (
        val input: ArrayList<String>
) {

    private val rules = mutableListOf<Rule>()
    private val nearbyTickets = mutableListOf<Ticket>()
    private var validTickets = listOf<Ticket>()
    private lateinit var myTicket: Ticket
    private var stage = ProcessingStage.Rules


    init {
        processInput(input)
    }

    private fun processInput(input: java.util.ArrayList<String>) {

        input.forEach { it ->
            if (it.isEmpty()) {
                if (!increaseStage()) return
                else return@forEach
            }

            when (stage) {
                ProcessingStage.Rules -> processRules(it)
                ProcessingStage.MyTicket -> processMyTicket(it)
                ProcessingStage.OtherTickets -> processTickets(it)
            }

            validTickets = nearbyTickets.filter { ticket -> ticket.sumOfInvalidFields(rules) == 0 }
        }
    }

    private fun processTickets(it: String) {
        if (it == "nearby tickets:") return
        nearbyTickets.add(Ticket(it.split(",").map { it.toInt() }))
    }

    private fun processMyTicket(it: String) {
        if (it == "your ticket:") return
        myTicket = Ticket(it.split(",").map { it.toInt() })
    }

    private fun processRules(it: String) {
        val parts = it.split(":")
        val name = parts[0]
        val ranges = parts[1].trim().split(" or ")
        val lowerRange = rangeFromString(ranges[0])
        val higherRange = rangeFromString(ranges[1])
        rules.add(Rule(name, lowerRange, higherRange))
    }

    private fun increaseStage(): Boolean {
        if (stage == ProcessingStage.Rules) {
            stage = ProcessingStage.MyTicket
            return true
        } else if (stage == ProcessingStage.MyTicket) {
            stage = ProcessingStage.OtherTickets
            return true
        }
        return false
    }

    private fun rangeFromString(s: String): Pair<Int, Int> {
        val parts = s.split("-")
        val lower = parts[0].toInt()
        val higher = parts[1].toInt()
        return Pair(lower, higher)
    }

    fun findSumOfInvalidFields(): Int {
        return nearbyTickets.sumBy { ticket ->
            ticket.sumOfInvalidFields(rules)
        }
    }
}

enum class ProcessingStage {
    Rules,
    MyTicket,
    OtherTickets
}