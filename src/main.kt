import day9.Day9


fun main(args: Array<String>) {
    Day9().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}