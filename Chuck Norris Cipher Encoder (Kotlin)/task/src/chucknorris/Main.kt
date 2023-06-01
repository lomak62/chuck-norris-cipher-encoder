package chucknorris
import kotlin.system.exitProcess
import java.util.Scanner
val scanner = Scanner(System.`in`)

fun convertToBinary(input: String): String {
    val binaryList = mutableListOf<String>()
    for (c in input) binaryList.add("%07d".format(Integer.toBinaryString(c.code).toInt()))
    return  binaryList.joinToString("")
}

fun cipherEncoder() {
    println("Input string:")
    val string = scanner.nextLine()
    val input = convertToBinary(string)

    val cipherList = mutableListOf<String>()
    var result = ""
    var block = ""
    var nextC = input[0]
    for (i in input.indices) {
        if (input[i] == nextC) {
            block += input[i]
        } else {
            cipherList.add(block)
            block = input[i].toString()
            nextC = input[i]
        }
        if (i == input.lastIndex) cipherList.add(block)
    }

    for (c in cipherList) {
        result += if (c[0] == '1') "0 ${"0".repeat(c.length)} " else "00 ${"0".repeat(c.length)} "
    }
    println("Encoded string:")
    println(result)
    println()
}

fun cipherDecoder() {
    println("Input encoded string:")
    val input = scanner.nextLine()
    if (encodedMessageValidator(input)) return

    var result = ""
    var binaryWord = ""
    val cList = input.split(" ").toMutableList()
    if (cList.size % 2 != 0) {
        errorMessage()
        return
    }
    for (i in 0 until cList.lastIndex step 2) {
        if (cList[i] != "0" && cList[i] != "00") {
            errorMessage()
            return
        }
        binaryWord += if (cList[i] == "0") "1".repeat(cList[i + 1].length) else "0".repeat(cList[i + 1].length)
    }
    if (binaryWord.length % 7 != 0) {
        errorMessage()
        return
    }
    for (i in 0..binaryWord.lastIndex step 7) {
        result += if (i == binaryWord.lastIndex - 6) {
            binaryWord.substring(i).toInt(2).toChar()
        } else {
            binaryWord.substring(i, i + 7).toInt(2).toChar()
        }
    }
    println("Decoded string:")
    println(result)
    println()
}

fun encodedMessageValidator(msg: String): Boolean {
    for (c in msg) if (c != ' ' && c != '0') {
        errorMessage()
        return true
    }
    return false
}

fun errorMessage() {
    println("Encoded string is not valid.")
    println()
}

fun exit() {
    println("Bye!")
    exitProcess(0)
}

fun misspelledOperation(operation: String) {
    println("There is no '$operation' operation")
    println()
}

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        when (val operation = scanner.nextLine()) {
            "encode" -> cipherEncoder()
            "decode" -> cipherDecoder()
            "exit" -> exit()
            else -> misspelledOperation(operation)
        }
    }
}