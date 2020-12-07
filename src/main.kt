import day6.Day6


fun main(args: Array<String>) {
    Day6().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}