package html

import java.lang.StringBuilder

fun html(block:Tag.()->Unit):Tag{
    return Tag("html").apply(block)
}


fun Tag.head(block:Head.()->Unit){
   this@head + Head().apply(block)
}

fun Tag.body(block:Body.()->Unit){
    this@body + Body().apply(block)
}

operator fun String.unaryPlus():String{
   return  StringBuilder(0).append("aaa").append(this).toString()
}

class StringNode(val content:String):Node{
    override fun render()=content
}

class Head:Tag("head")

class A:Tag("a"){

    val id="nAId"

    val href="http://www.hao123.com"

    override fun render(): String {
        return StringBuilder()
            .append("<$name ")
            .append("id=$id ")
            .append("href=$href ")
            .append(">")
            .append("首页")
            .append("</$name> \n")
            .toString()
    }
}


class Body:Tag("body"){
    var id by MapDelegate(properties)

    var `class` by MapDelegate(properties)

    var a:Node by ArrayDelegate(children)

    var b:Node by ArrayDelegate(children)
}
