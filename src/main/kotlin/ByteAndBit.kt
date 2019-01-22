import java.lang.StringBuilder

fun main(args: Array<String>) {
    var c = 'A'

    var cS ="好"

    var hz ='好'

    var ascii = c.toInt()

    var BitString =Integer.toBinaryString(ascii)

    //101100101111101
    //101101101010111
    with(StringBuilder()){
        cS.toCharArray().forEach {
            append(it.toInt())
        }
        var bits =Integer.toBinaryString(toString().toInt())

        println(bits)
    }

    val hzbit = Integer.toBinaryString(hz.toInt())
//
    println(hzbit)   //101100101111101

//    println(hza)

//    println(BitString.toByteArray().size)   //  1个中文占3个字符  1 个字符占 8byte  1 byte  = 8 bit  1000001
//
//    println(c.toString().toByteArray().size)
//    println(cS.toByteArray().size)
//    println(hz.toString().toByteArray().size)
}