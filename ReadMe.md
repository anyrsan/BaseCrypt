# 基本的一些加密算法

- 非对称加密，私钥加密，公钥解密 ， （公钥互换）[数字签名]

- 对称加密 （凯撒[Char to Int 位移]，DES加密[密钥为8位]，AES加密[密钥为16位，相对更安全，速度慢]）

- 消息（MD5,SHA1,SHA256），不可逆 [MessageDigestUtil提供相关api]

- 数字签名[SignatureUtil 提供相关api]

- 实现https服务配置，自处理证书[服务器为NodeJs]