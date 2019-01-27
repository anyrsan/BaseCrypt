# 基本的一些加密算法

- 非对称加密，私钥加密，公钥解密 ， （公钥互换）[数字签名]

- 对称加密 （凯撒[Char to Int 位移]，DES加密[密钥为8位]，AES加密[密钥为16位，相对更安全，速度慢]）

- 消息（MD5,SHA1,SHA256），不可逆 [MessageDigestUtil提供相关api]

- 数字签名[SignatureUtil 提供相关api]

- 实现https服务配置，自处理证书[服务器为NodeJs]




MIT License

Copyright (c) 2019 anyrsan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
