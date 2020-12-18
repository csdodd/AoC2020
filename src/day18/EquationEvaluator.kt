package day18

import java.util.*


class EquationEvaluator {

    fun evaluatePart1(equation: String): Long {
        val stack = Stack<String>()
        val parts = equation.split("").filter { it.isNotBlank() }

        for (i in parts.size - 1 downTo 0) {
            when (parts[i]) {
                "(" -> evaluateEquationInOrder(stack)
                else -> stack.push(parts[i])
            }
        }

        evaluateEquationInOrder(stack)

        return stack.pop().toLong()
    }

    fun evaluatePart2(equation: String): Long {
        val stack = Stack<String>()
        val parts = equation.split("").filter { it.isNotBlank() }

        for (i in parts.size - 1 downTo 0) {
            when (parts[i]) {
                "(" -> evaluateEquationWithPlusPrecedence(stack)
                else -> stack.push(parts[i])
            }
        }

        evaluateEquationWithPlusPrecedence(stack)

        return stack.pop().toLong()
    }

    private fun evaluateEquationInOrder(stack: Stack<String>) {
        var result = stack.pop().toLong()

        while (stack.count() > 0) {
            when (stack.pop()) {
                "+" -> result += stack.pop().toLong()
                "*" -> result *= stack.pop().toLong()
                ")" -> break
            }
        }

        stack.push(result.toString())
    }

    private fun evaluateEquationWithPlusPrecedence(stack: Stack<String>) {
        val innerStack = Stack<String>()

        outerLoop@ while (stack.count() > 0) {
            when (val value = stack.pop()) {
                "+" -> innerStack.push((stack.pop().toLong() + innerStack.pop().toLong()).toString())
                "*" -> innerStack.push(stack.pop())
                ")" -> break@outerLoop
                else -> innerStack.push(value)
            }
        }

        var result = 1L
        while (innerStack.count() > 0) {
            result *= innerStack.pop().toLong()
        }

        stack.push(result.toString())
    }
}