import com.sun.org.apache.xml.internal.security.utils.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object  AesCrypt{


    /**
     * 加密
     */
    fun encrypt(input:String,psd:String): String {
        val cipher = Cipher.getInstance("AES")

        val sks = SecretKeySpec(psd.toByteArray(),"AES")

        cipher.init(Cipher.ENCRYPT_MODE,sks)

        val encrypt = cipher.doFinal(input.toByteArray())

        return Base64.encode(encrypt)

    }


    /***
     * 解密
     */
    fun decrypt(input:String,psd:String): String {
        val cipher = Cipher.getInstance("AES")

        val sks = SecretKeySpec(psd.toByteArray(),"AES")

        cipher.init(Cipher.DECRYPT_MODE,sks)

        val encrypt = cipher.doFinal(Base64.decode(input))

        return String(encrypt)

    }
}

fun main(args: Array<String>) {

    val str = "你好呀，这是什么？"

    val psd = "1234567812345678"  // AES 要 16位秘钥

    AesCrypt.encrypt(str,psd).let { encrypt->
            println("加密:"+encrypt)
        AesCrypt.decrypt(encrypt,psd).let {decrypt->
            println("解密："+ decrypt)
        }

    }

}