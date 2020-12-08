import day8.Day8


fun main(args: Array<String>) {
    Day8().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}