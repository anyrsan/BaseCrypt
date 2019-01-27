# 基本的一些加密算法

- 非对称加密，私钥加密，公钥解密 ， （公钥互换）[数字签名]

- 对称加密 （凯撒[Char to Int 位移]，DES加密[密钥为8位]，AES加密[密钥为16位，相对更安全，速度慢]）

- 消息（MD5,SHA1,SHA256），不可逆 [MessageDigestUtil提供相关api]

- 数字签名[SignatureUtil 提供相关api]

- 实现https服务配置，自处理证书[服务器为NodeJs]














### License

    Copyright (c) 2019 anyrsan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.