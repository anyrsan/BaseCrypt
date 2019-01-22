import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    application
}



application{
    mainClassName = "MainKt"
}

dependencies{
    compile(kotlin("stdlib"))
}

repositories{
    mavenCentral()
    jcenter()
}


task("getsrcname",{
    // 增量更新
    inputs.dir("src")  // 输入  会检查这个文件目录 有无变化
    outputs.dir("one.txt") // 输出  会检查这个文件是否有输出变化
    doFirst{
       var srcDir = fileTree("src")
        var oneWrite = File("one.txt")
        oneWrite.writeText("")
        srcDir.forEach{
            if(it.isFile){
                oneWrite.appendText(it.absolutePath)
                oneWrite.appendText("\t\n")
            }
        }
    }
})


//task("myCopy",Copy::class){
//    doFirst{
//        from("src/main")
//        into("backup")
//    }
//}
//task("myDelete",Delete::class,{
//    setDelete("backup/main")
//})

task("myCopy",Copy::class,{

        from("src")
        println("from...")
        into("backup")

})
group = "org.liu.huan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}