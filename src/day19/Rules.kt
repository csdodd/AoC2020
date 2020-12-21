package day19

class Rules(input: ArrayList<String>) {

    private val rules = mutableMapOf<Int, Rule>()

    init {
        parseRules(input)
    }

    fun isValid(phrase: String, pda: List<Int>): Boolean {
        if (pda.isEmpty()) {
            return phrase.isEmpty()
        }
        return when (val rule = rules[pda.first()]) {
            is Rule.Letter -> phrase.startsWith(rule.value) && isValid(phrase.drop(1), pda.drop(1))
            is Rule.Branch -> rule.branches.firstOrNull { branch -> isValid(phrase, branch + pda.drop(1)) } != null
            else -> false
        }
    }

    private fun parseRules(input: java.util.ArrayList<String>) {
        input.forEach {
            if (it.isEmpty()) return

            val parts = it.split(":")
            val ruleNumber = parts[0].toInt()

            when {
                parts[1].contains('"') -> {
                    val letter = parts[1].replace("\"", "").trim()
                    rules[ruleNumber] = Rule.Letter(letter.first())
                }
                else -> {
                    val branches = parts[1]
                            .split("|")
                            .map { s -> s.trim() }
                            .map { s -> s.split(' ').map { s -> s.toInt() } }
                    rules[ruleNumber] = Rule.Branch(branches)
                }
            }
        }
    }
}