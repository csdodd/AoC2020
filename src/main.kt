import day1.Day1
import day2.Day2


fun main(args: Array<String>) {
    Day1().run()
    Day2().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}