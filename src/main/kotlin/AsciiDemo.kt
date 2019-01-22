import java.lang.StringBuilder


fun main(args: Array<String>) {

    val str = "I love you"

   val result =  with(StringBuilder()){
        for(s in str){
            append(s.toInt().toString() + " ")
        }
        toString()
    }

    println(result)

}