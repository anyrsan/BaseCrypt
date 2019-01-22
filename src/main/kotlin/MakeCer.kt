import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import java.io.File
import java.security.KeyPair
import java.security.KeyStore
import java.security.PrivateKey


fun main(args: Array<String>) {

    makePem()


    writeToFile(File("src/any.cer"),"src/publicCer.cer")
}


fun makePem(){
    //    //
    val alias = "anyrsan"
    val password = "123456"

    val keystore = KeyStore.getInstance("JKS")

    keystore.load(File("src/anyrsan.keystore").inputStream(), password.toCharArray())

    val keyPair = getPrivateKey(keystore, alias, password)

    val privateKey = keyPair.private

    var privateEncoded = Base64.encode(privateKey.encoded)

    writeToFile(File("src/privateKey.pem"),privateEncoded,0)

    val publicKey = keyPair.public

    var publicEncoded = Base64.encode(publicKey.encoded)

    writeToFile(File("src/publicKey.pem"),publicEncoded,1)
}


fun writeToFile(sourceFile:File,textName: String) {

    val arrayByte = sourceFile.readBytes()
    val encode = Base64.encode(arrayByte)

    val fileName = File(textName)

    fileName.writeText("")

    fileName.appendText("-----BEGIN CERTIFICATE-----\n")
    subText(encode,64).forEach {
        fileName.appendText(it + "\n")
    }
    fileName.appendText("-----END CERTIFICATE-----")

}


fun writeToFile(fileName: File, encode: String, flag: Int) {

    val keyword = when (flag) {
        0 -> "PRIVATE KEY"
        1 -> "CERTIFICATE"
        else -> ""
    }

    //清空文件
    fileName.writeText("")

    fileName.appendText("-----BEGIN $keyword-----\n")


    subText(encode,64).forEach {
        fileName.appendText(it + "\n")
    }

    fileName.appendText("-----END $keyword-----")

}


fun getPrivateKey(keystore: KeyStore, alias: String, password: String): KeyPair {
    val privateKey = keystore.getKey(alias, password.toCharArray()) as PrivateKey
    val cert = keystore.getCertificate(alias)
    val publicKey = cert.publicKey
    return KeyPair(publicKey, privateKey)
}


fun subText(input: String, subSize: Int): List<String> {
    var list = arrayListOf<String>()
    var temp = input
    while (temp.isNotEmpty()) {
        val size = if (temp.length > subSize) subSize else temp.length
        val str = temp.substring(0, size)
        list.add(str)
        temp = temp.substring(size)
    }
    return list
}
