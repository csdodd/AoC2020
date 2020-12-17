package day16

data class Rule(
        val name: String,
        private val lowerRange: Pair<Int,  Int>,
        private val higherRange: Pair<Int,  Int>,
) {
    private val validNumbers = mutableMapOf<Int, Boolean>()
    private val numValidTicketFields = mutableMapOf<Int, Int>()

    init {
        for (i in lowerRange.first .. lowerRange.second) {
            validNumbers[i] = true
        }

        for (i in higherRange.first .. higherRange.second) {
            validNumbers[i] = true
        }
    }

    fun isValidNumber(number: Int) = validNumbers[number] == true

    fun addValidTicketField(fieldIndex: Int) {
        val currentValue =  numValidTicketFields[fieldIndex] ?: 0
        numValidTicketFields[fieldIndex] = currentValue + 1
    }

    fun validTicketFieldIndexes(ticketCount: Int) = numValidTicketFields.filter { it.value == ticketCount }
}