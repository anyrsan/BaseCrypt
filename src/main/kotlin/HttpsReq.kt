

import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URL
import java.security.cert.CertificateFactory
import java.security.KeyStore
import javax.net.ssl.*


fun main(args: Array<String>) {


    val url = URL("https://127.0.0.1:3030/json")

   // Create an SSLContext that uses our TrustManager
    val context = SSLContext.getInstance("TLS")

    // Create a TrustManager that trusts the CAs in our KeyStore
    val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)

    val keyStoreType = KeyStore.getDefaultType()
    val keyStore = KeyStore.getInstance(keyStoreType)
    keyStore.load(null, null)
    var cf=  CertificateFactory.getInstance("X.509")
    val fileInputStream = File("key/anyrsan.cer").inputStream()
    var cert = cf.generateCertificate(fileInputStream)

    keyStore.setCertificateEntry("anyrsan",cert)
    tmf.init(keyStore)

    context.init(null, tmf.trustManagers, null)
    val urlConnection = url.openConnection() as HttpsURLConnection
    urlConnection.sslSocketFactory = context.socketFactory
    urlConnection.hostnameVerifier = MyHostnameVerifier()

    val inputStream = urlConnection.inputStream
    val baos = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var len = inputStream.read(buffer)
    while (len != -1 ){
        baos.write(buffer,0,len)
        len = inputStream.read(buffer)
    }
    println("请求结果： "+ baos.toString())




}

class MyHostnameVerifier :HostnameVerifier{
    override fun verify(p0: String?, p1: SSLSession?): Boolean {
        return true
    }

}