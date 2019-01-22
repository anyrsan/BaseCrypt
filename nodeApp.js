var express = require('express')
var app = express()

var fs = require('fs')
var path = require('path')
var http = require('http')
var https = require('https')



//根据项目的路径导入生成的证书文件
var privateKey = fs.readFileSync(path.join(__dirname, './keyCertificate/anyrsan.pem'), 'utf8');
var certificate = fs.readFileSync(path.join(__dirname, './keyCertificate/anyrsan.cer'), 'utf8');
var credentials = {
    key: privateKey,
    cert: certificate
};

var httpServer = http.createServer(app);
var httpsServer = https.createServer(credentials, app)

var PORT = 3000
var SSLPORT = 3030

//创建http服务器
httpServer.listen(PORT, function () {
    console.log('HTTP Server is running on: http://localhost:%s', PORT);
})

//创建https服务器
httpsServer.listen(SSLPORT, function () {
    console.log('HTTPS Server is running on: https://localhost:%s', SSLPORT);
})