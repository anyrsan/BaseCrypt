import java.io.File
import java.io.FileWriter

fun main(args: Array<String>) {

//    File("ktdemo.iml").readText().toCharArray().filterNot(Char::isWhitespace).groupBy { it }
//        .map { it.key to it.value.size }.sortedByDescending { it.second }.forEach(::println)

//    val cmd =File("src/at_en.txt").readText()
//
//    var caesarCrypt = CaesarCrypt()

//    val key =3
//
//    var encryptString = caesarCrypt.encrypt(cmd,key)
//
//    File("src/at_en.txt").writeText(encryptString)


//    getMaxChar("src/at_en.txt")?.let {
//        println(it.first.toString()  + it.second.toString())
//
//        var c = it.first
//
//        var ascii = c.toInt()
//
//        var e = 'r'
//
//        var ea = e.toInt()
//
//
//        val key = ea - ascii
//
//        println("a $ascii  ea $ea  key $key")
//
//        println("decrypt: "+ caesarCrypt.decrypt(cmd,key))

//    }


    //  e出现的最多  e  -->  o  有多少个

    val str ="好好学习，天天向上"

    val bys = ByteUtil.bytesToHexString(str.toByteArray())

    println(bys.length)

    println(ByteUtil.hexStringToBytes(bys))

}


fun getMaxChar(fileName:String):Pair<Char,Int>?{
        File("src/at.txt").readText().toCharArray().filterNot (Char::isWhitespace).groupBy { it }
        .map { it.key to it.value.size }.sortedByDescending { it.second }.let {
               return it.first()
            }
    return null
}



