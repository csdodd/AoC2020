import day1.Day1
import day2.Day2
import day3.Day3
import day4.Day4


fun main(args: Array<String>) {
    Day1().run()
    Day2().run()
    Day3().run()
    Day4().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}