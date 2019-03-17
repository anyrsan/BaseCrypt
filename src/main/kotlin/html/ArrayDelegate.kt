package html

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ArrayDelegate(val array:MutableList<Node>):ReadWriteProperty<Any,Node>{
    override fun getValue(thisRef: Any, property: KProperty<*>): Node {
        println("get ... thisRef...."+thisRef)
        println(property.name)
       return array[array.size-1]
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Node) {
        println("set...thisRef...."+thisRef)
        println(property.name  + "" + array.size)
       array.add(value)
    }


}