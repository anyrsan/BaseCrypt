package html

import java.io.File

fun main(args: Array<String>) {
//    Tag("html").apply {
//        properties["id"]="htmlId"
//        children.add(Tag("head"))
//    }.render().let(::println)

//    html{
//        properties["id"]="htmlId"
//        children.add(Tag("head"))
//    }.render().let(::println)


//    html{
//        "id"("htmlId")
//        children.add(Tag("head"))
//    }.render().let(::println)


//    println((+"cccc")+(+"bbbb"))
//    println("bb"+"bbb")

//    html{
//        "id"("htmlId")
//        "head"{
//
//        }
//        "body"{
//            "a"{
//                "id"("aId")
//                "href"("http://www.baidu.com")
////                "百度".aaa()
//                +"百度"
//            }
//        }
//    }.render().let(::println)

    var file = File("temp.html")

    //<meta http-equiv=Content-Type content="text/html;charset=utf-8">
   var str = html{
        "id"("htmlId")
       "meta"{
            "http-equiv"("Content-Type")
           "content"("text/html;charset=utf-8")
       }
        head{
            "id"("headId")
        }
       body{
           a=StringNode("aaa<br>")

           id = "bodyId"
           `class`="bodyClass"
            "a"{
                "id"("aId")
                "href"("http://www.baidu.com")
//                "百度".aaa()
                +"百度"
            }
           a=StringNode("<br>ccc<br>")

           "div"{
               id="divId"
               "span"{
                   +"你好呀"
               }
           }

           a=StringNode("ddd<br>")


           b=A()
        }
    }.render()


    file.writeText(str)

//    head{
//        "id"("headId")
//    }.render().let(::println)
}