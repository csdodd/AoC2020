package day19

sealed class Rule {
    data class Letter(val value: Char) : Rule()
    data class Branch(val branches: List<List<Int>>) : Rule()
}