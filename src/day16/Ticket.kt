package day16

data class Ticket (
        val values: List<Int>
) {
    fun sumOfInvalidFields(rules: MutableList<Rule>): Int {
        return values.filter { !isValid(it, rules) }.sum()
    }

    private fun isValid(value: Int, rules: MutableList<Rule>): Boolean {
        var invalidCount = 0
        rules.forEach {  rule ->
            if (!rule.isValidNumber(value)) invalidCount++
        }
        return invalidCount < rules.size
    }
}