import day10.Day10


fun main(args: Array<String>) {
    Day10().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}