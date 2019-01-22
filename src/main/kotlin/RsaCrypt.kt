import com.sun.org.apache.xml.internal.security.utils.Base64
import java.io.ByteArrayOutputStream
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/***
 *
 * 非对称加密
 */

object RsaCrypt {
    val transformation = "RSA"
    val MAX_ENCRYPT_SIZE = 117
    val MAX_DECRYPT_SIZE = 256

    /**
     * 私钥加密
     *      分段加密
     */
    fun encryptByPrivateKey(input: String, key: PrivateKey): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val byteArray = input.toByteArray()
        val maxByteSize = byteArray.size
        var temp: ByteArray? = null
        var offset = 0//偏移量

        val outputStream = ByteArrayOutputStream()

        while (maxByteSize - offset > 0) {
            if (maxByteSize - offset >= MAX_ENCRYPT_SIZE) {
                temp = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_SIZE)
                offset += MAX_ENCRYPT_SIZE
            } else {  // 最后 一块
                temp = cipher.doFinal(byteArray, offset, maxByteSize - offset)
                offset = maxByteSize
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return Base64.encode(outputStream.toByteArray())
    }

    /***
     * 公钥加密
     *    分段加密
     */
    fun encryptByPublicKey(input: String, key: PublicKey): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val byteArray = input.toByteArray()
        val maxByteSize = byteArray.size
        var temp: ByteArray? = null
        var offset = 0//偏移量

        val outputStream = ByteArrayOutputStream()

        while (maxByteSize - offset > 0) {
            if (maxByteSize - offset >= MAX_ENCRYPT_SIZE) {
                temp = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_SIZE)
                offset += MAX_ENCRYPT_SIZE
            } else {  // 最后 一块
                temp = cipher.doFinal(byteArray, offset, maxByteSize - offset)
                offset = maxByteSize
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return Base64.encode(outputStream.toByteArray())
    }

    /***
     * 私钥解密
     */
    fun decryptByPrivateKey(input: String, key: PrivateKey): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val byteArray = Base64.decode(input)
        val maxByteSize = byteArray.size
        var temp: ByteArray? = null
        var offset = 0//偏移量
        val outputStream = ByteArrayOutputStream()
        while (maxByteSize - offset > 0) {
            if (maxByteSize - offset >= MAX_DECRYPT_SIZE) {
                temp = cipher.doFinal(byteArray, offset, MAX_DECRYPT_SIZE)
                offset += MAX_DECRYPT_SIZE
            } else {  // 最后 一块
                temp = cipher.doFinal(byteArray, offset, maxByteSize - offset)
                offset = maxByteSize
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return String(outputStream.toByteArray())
    }


    /***
     * 私钥
     */
    fun getPrivateKey(): PrivateKey {
        val privateStr = """MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDOniFHIvV9IYOedtw0Uw1TtgkU
 kQ65CVWZ4qJcpBcIVBIVRPwjMtcVxXdC7R2nfFASI7+XNjjweEm+2nx8LOUE4pIlU3MnTrTaAIGI
            92ZFSyxHi/x/2b2yKArP4YJRLpTm8yzTfL4Mng/gZoJT9Oxpt0qELcfHMybydKyGhp1RQD2B0rm4
    IQUU2tVqIYATycCoVyno/BOAoaLnQ4C4E+8QroqU8nZEXAyT7plp1xXVNbSeLwxY2nCo3M/272XD
    VUEnagZVxOoawmQZIImEmMuFIFFBzKJOmYbg4a7Kq2ND/ndIjisQ5VoVGXyUQY23rFkVsF3wPAIs
    I+BaMdNOaDBJAgMBAAECggEAdvqyOQ+Vg+AHtfnGJk9pDjp2EiTR8JXCeGH8kxJevQoEe8EE3JQL
    LVtPqPd4Kxk31qpFSqMLuNXbyY5xPuRvYRfDhsD+P7VgCWVfMmsctmxqbCzdUxYk2XGbgEfgzjTf
    w/pmZsaffg6LH/KHvCb++IV24JSoRTpchCPRsYnVD5LAa2aXWeL4eWyUbIxxt2ThU80CiJHuvJHa
            4Pg/OKchqrd7Scve7/YDkNmzHvKTaiIhCsTImABIJF5A1FR0Xg4wSe6q9Hsb8jwGKIbNvUuvZNYF
            qpuNRXq/qHg66cHzuCEZVfmc4PfvQ9NWtJTdGc5IkWeEOdD/jInsSZReDDSa3QKBgQDxLo8OQ9vM
    ZwZ0CbB06E0XhAb7PDLQbf/x60YLdbwTLWK7kj99zNUk5NVnarMEJtCGHzD9K1+yGj3b6gWxDBSz
    EztubVHxG04mWb7J6Bl0Oy5u1Q9IbvBoy9o3peH5pTQaG1hvQxmpVk9JP41QXrvh4Xe8yRVfV3aC
    upu0ZzyM3wKBgQDbT+1DmLjuRpCmGUw94IvbhI3lTuS9Vzw4wYXWcjKrhChPlrEzr/qiM8KeWBLi
            WTEOeN/8YdkyJ+48m98xkzVAX7BOlbmGNM5UTjiJja+yBUDD/y3OwkEZcpSLbtsia7ndDrELOifO
    z6LDyppiIt9sUZYL8vUpeLRHepXKdX0/1wKBgBP6rGUdH/clzbYNG24D1GcPS9jCGt+DTUGIIOxQ
    G31nNBefvNy5/CorPrZrc1rzsDB8Iv3F+w5w+BDgqQoKmyhefnzeV3xmq6mno5NsQ7eKM4WA5tw0
    PHwh7vjPKBIbKl7DRycoYtbRTI4f0qAGyP+kQtwDuF6Gi023EbD3IF/3AoGAKswFG/zQkcb5cUBc
            jXKlODg8zHPB1WURnm29ZWSxmKDYkPdyKwj7Wu3I28eKH9uR5CYYZ5LcpJoANpmgS3dTm/VQXXWA
    V700SmVdSRJ6J744rka8tK1ZsNKYREFkx+nHwmiciflWTVhdheGikOYHPYcKRdiqmOYyIAS++FxH
            Dc8CgYA5QoqonnBnEOYZKe4obSeLtPYVIM+H1OB4KBAq2FZ827EvBjNEmK4QNjQbiT1D0fuG3K0i
    HgCv7Qcj2EECC4LpDG7m5hLVv5GYdSn7YE/Yi+nJet/r/psWrW5MTYRyuG4lwb7Uk8ifUXh2KdW3
    UzbXkMzj3WtwpQXqgKjwDZsjQg=="""

        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateStr)))
    }

    /***
     * 公钥
     */
    fun getPublicKey(): PublicKey {
        val publicStr = """MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzp4hRyL1fSGDnnbcNFMNU7YJFJEOuQlV
    meKiXKQXCFQSFUT8IzLXFcV3Qu0dp3xQEiO/lzY48HhJvtp8fCzlBOKSJVNzJ0602gCBiPdmRUss
    R4v8f9m9sigKz+GCUS6U5vMs03y+DJ4P4GaCU/TsabdKhC3HxzMm8nSshoadUUA9gdK5uCEFFNrV
    aiGAE8nAqFcp6PwTgKGi50OAuBPvEK6KlPJ2RFwMk+6ZadcV1TW0ni8MWNpwqNzP9u9lw1VBJ2oG
            VcTqGsJkGSCJhJjLhSBRQcyiTpmG4OGuyqtjQ/53SI4rEOVaFRl8lEGNt6xZFbBd8DwCLCPgWjHT
            TmgwSQIDAQAB"""

        // 根据 string 获取 秘钥
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr)))
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
    //生成秘钥对

//
//    println("privateKey=${Base64.encode(privateKey.encoded)}")
//    println("publicKey=${Base64.encode(publicKey.encoded)}")
    // 保存 秘钥对

    // 公钥加密 私钥解密  对称加密


    val privateStr = """MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDOniFHIvV9IYOedtw0Uw1TtgkU
 kQ65CVWZ4qJcpBcIVBIVRPwjMtcVxXdC7R2nfFASI7+XNjjweEm+2nx8LOUE4pIlU3MnTrTaAIGI
            92ZFSyxHi/x/2b2yKArP4YJRLpTm8yzTfL4Mng/gZoJT9Oxpt0qELcfHMybydKyGhp1RQD2B0rm4
    IQUU2tVqIYATycCoVyno/BOAoaLnQ4C4E+8QroqU8nZEXAyT7plp1xXVNbSeLwxY2nCo3M/272XD
    VUEnagZVxOoawmQZIImEmMuFIFFBzKJOmYbg4a7Kq2ND/ndIjisQ5VoVGXyUQY23rFkVsF3wPAIs
    I+BaMdNOaDBJAgMBAAECggEAdvqyOQ+Vg+AHtfnGJk9pDjp2EiTR8JXCeGH8kxJevQoEe8EE3JQL
    LVtPqPd4Kxk31qpFSqMLuNXbyY5xPuRvYRfDhsD+P7VgCWVfMmsctmxqbCzdUxYk2XGbgEfgzjTf
    w/pmZsaffg6LH/KHvCb++IV24JSoRTpchCPRsYnVD5LAa2aXWeL4eWyUbIxxt2ThU80CiJHuvJHa
            4Pg/OKchqrd7Scve7/YDkNmzHvKTaiIhCsTImABIJF5A1FR0Xg4wSe6q9Hsb8jwGKIbNvUuvZNYF
            qpuNRXq/qHg66cHzuCEZVfmc4PfvQ9NWtJTdGc5IkWeEOdD/jInsSZReDDSa3QKBgQDxLo8OQ9vM
    ZwZ0CbB06E0XhAb7PDLQbf/x60YLdbwTLWK7kj99zNUk5NVnarMEJtCGHzD9K1+yGj3b6gWxDBSz
    EztubVHxG04mWb7J6Bl0Oy5u1Q9IbvBoy9o3peH5pTQaG1hvQxmpVk9JP41QXrvh4Xe8yRVfV3aC
    upu0ZzyM3wKBgQDbT+1DmLjuRpCmGUw94IvbhI3lTuS9Vzw4wYXWcjKrhChPlrEzr/qiM8KeWBLi
            WTEOeN/8YdkyJ+48m98xkzVAX7BOlbmGNM5UTjiJja+yBUDD/y3OwkEZcpSLbtsia7ndDrELOifO
    z6LDyppiIt9sUZYL8vUpeLRHepXKdX0/1wKBgBP6rGUdH/clzbYNG24D1GcPS9jCGt+DTUGIIOxQ
    G31nNBefvNy5/CorPrZrc1rzsDB8Iv3F+w5w+BDgqQoKmyhefnzeV3xmq6mno5NsQ7eKM4WA5tw0
    PHwh7vjPKBIbKl7DRycoYtbRTI4f0qAGyP+kQtwDuF6Gi023EbD3IF/3AoGAKswFG/zQkcb5cUBc
            jXKlODg8zHPB1WURnm29ZWSxmKDYkPdyKwj7Wu3I28eKH9uR5CYYZ5LcpJoANpmgS3dTm/VQXXWA
    V700SmVdSRJ6J744rka8tK1ZsNKYREFkx+nHwmiciflWTVhdheGikOYHPYcKRdiqmOYyIAS++FxH
            Dc8CgYA5QoqonnBnEOYZKe4obSeLtPYVIM+H1OB4KBAq2FZ827EvBjNEmK4QNjQbiT1D0fuG3K0i
    HgCv7Qcj2EECC4LpDG7m5hLVv5GYdSn7YE/Yi+nJet/r/psWrW5MTYRyuG4lwb7Uk8ifUXh2KdW3
    UzbXkMzj3WtwpQXqgKjwDZsjQg=="""

    val publicStr = """MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzp4hRyL1fSGDnnbcNFMNU7YJFJEOuQlV
    meKiXKQXCFQSFUT8IzLXFcV3Qu0dp3xQEiO/lzY48HhJvtp8fCzlBOKSJVNzJ0602gCBiPdmRUss
    R4v8f9m9sigKz+GCUS6U5vMs03y+DJ4P4GaCU/TsabdKhC3HxzMm8nSshoadUUA9gdK5uCEFFNrV
    aiGAE8nAqFcp6PwTgKGi50OAuBPvEK6KlPJ2RFwMk+6ZadcV1TW0ni8MWNpwqNzP9u9lw1VBJ2oG
            VcTqGsJkGSCJhJjLhSBRQcyiTpmG4OGuyqtjQ/53SI4rEOVaFRl8lEGNt6xZFbBd8DwCLCPgWjHT
            TmgwSQIDAQAB"""


    // 根据 string 获取 秘钥
    val kf = KeyFactory.getInstance("RSA")
    val privateKey = kf.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateStr)))
    val publicKey = kf.generatePublic(X509EncodedKeySpec(Base64.decode(publicStr)))

//
//    // 加密的内容大于117个字节时，会超出加密，所以采用分段加密
    val input = "你好呀，你呀呀你好呀"

    //公钥加密 ， 私钥解密
    RsaCrypt.encryptByPublicKey(input, publicKey).let {
        println("pk:" + it)

        RsaCrypt.decryptByPrivateKey(it, privateKey).let {
            println("de=" + it)
        }

    }


}