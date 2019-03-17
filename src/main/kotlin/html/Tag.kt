package html

import java.lang.StringBuilder

open class Tag(val name:String):Node{
     val children = ArrayList<Node>()

    val properties = HashMap<String,String>()

    operator fun String.invoke(value:String){
        properties[this] = value
    }

    operator fun String.invoke(block:Tag.()->Unit){
        children.add(Tag(this).apply(block))
    }

//    fun String.aaa(){
//        children.add(StringNode(this))
//    }

     operator fun String.unaryPlus(){
        children.add(StringNode(this))
    }

    operator fun plus(node:Node){
        children.add(node)
    }

    override fun render(): String {
        return StringBuilder()
            .append("<$name")
            .let {
                stringBuilder ->
                    if(!this.properties.isEmpty()){
                        stringBuilder.append(" ")
                        this.properties.forEach{
                            stringBuilder.append(it.key)
                                .append("=\"")
                                .append(it.value)
                                .append("\"")
                        }

                    }
                stringBuilder
            }
            .append(">")
            .let{
                stringBuilder ->
                    children.map(Node::render).map(stringBuilder::append)
                    stringBuilder
            }
            .append("</$name>\n")
            .toString()
    }
}