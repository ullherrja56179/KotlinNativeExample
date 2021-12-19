import kotlinx.cinterop.*
import kotlinx.cinterop.alloc

fun main() {
    //memCoped = automatically free alloced memory
    memScoped {
        val sysInfo = alloc<libsys.sysinfo>()
        libsys.sysinfo(sysInfo.ptr)
        println("RAM IS: ${sysInfo.totalram}")
    }

    libstat.mkdir("/home/jakob/Desktop/Test", libstat.S_IRWXU)
}