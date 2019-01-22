import java.lang.StringBuilder

class CaesarCrypt{

    /**
     * 加密
     */
    fun encrypt(cmd:String,key:Int):String{
      return  with(StringBuilder()){

           cmd.toCharArray().forEach {
               var c = it
               //得到ascii
               var ascii = c.toInt()
               //移动
               ascii+=key
               //获取新的char
               var result = ascii.toChar()

               append(result)
           }

           toString()
       }

    }


    /**
     * 解密
     */
    fun decrypt(cmd:String,key:Int):String{
        return  with(StringBuilder()){

            cmd.toCharArray().forEach {
                var c = it
                //得到ascii
                var ascii = c.toInt()
                //移动
                ascii-=key
                //获取新的char
                var result = ascii.toChar()

                append(result)
            }

            toString()
        }

    }
}

/**
 * 凯撒算法
 *
 * 原理 ：移动ascii码，
 *
 *  A  ->  移动3位 得到 D
 *
 */
fun main(args: Array<String>) {

    //指令
    val cmd ="我爱你"
    // 秘钥
    val key = 3


   CaesarCrypt().encrypt(cmd,key).let {
       println("encrypt: "+it)

       CaesarCrypt().decrypt(it,key).let {
           println("decrypt"+it)
       }

   }

}


fun demo(){
    val c = 'A'

    var ascii = c.toInt()

    val key =3

    ascii+=key

    val result = ascii.toChar()

    println(result)
}