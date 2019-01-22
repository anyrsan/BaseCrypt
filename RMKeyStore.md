# 生成 keystore 

 ## keytool 生成 xx.keystore
 
 keytool -genkeypair -alias anyrsan -keystore anyrsan.keystore -keyalg RSA -validity 3650   
 
 ##  keytool 生成 pkcs12
 
 keytool -importkeystore -srckeystore anyrsan.keystore -destkeystore anyrsan.keystore -deststoretype pkcs12 
 
 ## keytool 导出 xx.cer
 
 keytool  -exportcert -alias anyrsan -keystore .\anyrsan.keystore -file any.cer
 
 
 ## 转成pem 并且 base64 cer
 
 - 写代码实现 

 - 或者 用openssl  

 - 或者在线转  [地址](http://www.ssleye.com/jks_pkcs12.html)
 
 