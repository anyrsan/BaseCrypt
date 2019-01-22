import java.lang.StringBuilder
import java.security.MessageDigest

object MessageDigestUtil {


    /**
     * 处理成32位的字节长度
     */
    fun getMd5(input:String):String{
        val digest = MessageDigest.getInstance("MD5")
        val bytes = digest.digest(input.toByteArray())
        // 当前是16位长度
//        println("md5转后："+bytes.size)
        // 转成16进制的数据
        return toHex(bytes)
    }

    /***
     * sha1 加密处理
     */
    fun getSha1(input:String):String{
        val digest = MessageDigest.getInstance("SHA-1")
        val bytes = digest.digest(input.toByteArray())
        return toHex(bytes)
    }

    /**
     * SHA-256处理
     */
    fun getSha256(input:String):String{
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(input.toByteArray())
        return toHex(bytes)
    }

    /**
     * 16 进制转换
     */
    fun toHex(byteArray:ByteArray):String{
       return with(StringBuilder()){
           byteArray.forEach {
                val hex =it.toInt() and (0xFF)
                val toHexString = Integer.toHexString(hex)
                if(toHexString.length ==1){
                    append("0").append(toHexString)
                }else{
                    append(toHexString)
                }
            }

            toString()
        }
    }

}

fun main(args: Array<String>) {
    val input = "你好呀"
    println(MessageDigestUtil.getMd5(input))
    println(MessageDigestUtil.getMd5(input).toByteArray().size)

    println(MessageDigestUtil.getSha1(input))
    println(MessageDigestUtil.getSha1(input).toByteArray().size)

    println(MessageDigestUtil.getSha256(input))
    println(MessageDigestUtil.getSha256(input).toByteArray().size)


}