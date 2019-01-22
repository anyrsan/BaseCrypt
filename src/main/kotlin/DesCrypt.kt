import com.sun.org.apache.xml.internal.security.utils.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec


object  DesCrypt {


    /**
     * 加密
     */
    fun encrypt(input: String, psd: String): String {
        val cipher = Cipher.getInstance("DES")

        val kf = SecretKeyFactory.getInstance("DES")

        val kyp = DESKeySpec(psd.toByteArray())

        val key = kf.generateSecret(kyp)

        cipher.init(Cipher.ENCRYPT_MODE, key)

        val encrypt = cipher.doFinal(input.toByteArray())

        return Base64.encode(encrypt)
    }


    /***
     * 解密
     */
    fun decrypt(input: String, psd: String): String {
        val cipher = Cipher.getInstance("DES")

        val kf = SecretKeyFactory.getInstance("DES")

        val kyp = DESKeySpec(psd.toByteArray())

        val key = kf.generateSecret(kyp)

        cipher.init(Cipher.DECRYPT_MODE, key)

        val encrypt = cipher.doFinal(Base64.decode(input))

        return String(encrypt)
    }

}

fun main(args: Array<String>) {

    val str = "你好呀，这是什么？"

    val psd = "12345678"   //DES 要 16位秘钥

    DesCrypt.encrypt(str,psd).let { encrypt->
            println("加密:$encrypt")
        DesCrypt.decrypt(encrypt,psd).let {decrypt->
            println("解密：$decrypt")
        }

    }

}