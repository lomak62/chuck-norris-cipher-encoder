// write your function here
fun isGreater(n1: Int, n2: Int, n3: Int, n4: Int): Boolean = n1 + n2 > n3 + n4

fun main() {
    val number1 = readLine()!!.toInt()
    val number2 = readLine()!!.toInt()
    val number3 = readLine()!!.toInt()
    val number4 = readLine()!!.toInt()

    println(isGreater(number1, number2, number3, number4))
}