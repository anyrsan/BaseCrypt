import com.sun.org.apache.xml.internal.security.utils.Base64
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature

/***
 * 数字签名类
 */
object SignatureUtil {

    //生成签名
    fun makeSign(input: String, privateKey: PrivateKey): String {
        val signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(privateKey)
        signature.update(input.toByteArray())
        return Base64.encode(signature.sign())

    }

    /***
     * 验证签名
     */
    fun verifySign(input: String, publicKey: PublicKey, sign: String): Boolean {
        val signature = Signature.getInstance("SHA256withRSA")
        signature.initVerify(publicKey)
        signature.update(input.toByteArray())
        return signature.verify(Base64.decode(sign.toByteArray()))
    }


    /***
     * 生成秘钥对
     * 注意保存好
     */
    fun makeKey(): Pair<PrivateKey, PublicKey> {
        val generator = KeyPairGenerator.getInstance("RSA")
        val keyPair = generator.genKeyPair() //生成秘钥对
        val publicKey = keyPair.public //公钥
        val privateKey = keyPair.private//私钥
        return Pair(privateKey,publicKey)
    }


}

fun main(args: Array<String>) {
    val input ="name=123&price=788"

    SignatureUtil.makeSign(input,RsaCrypt.getPrivateKey()).let {
        println("签名：$it")
        SignatureUtil.verifySign(input+"aaa",RsaCrypt.getPublicKey(),it).let {
            println("验证结果：$it")
        }

    }
}