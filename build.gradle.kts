plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "me.jakob"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Linux" -> linuxX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations.getByName("main") {
            val libcurl by cinterops.creating {
                file("/home/jakob/IdeaProjects/Native/src/nativeInterop/cinterop/libcurl.def")
                packageName("libcurl")
            }
            val sys by cinterops.creating {
                file("/home/jakob/IdeaProjects/Native/src/nativeInterop/cinterop/sys.def")
                packageName("libsys")
            }
            val stat by cinterops.creating {
                file("/home/jakob/IdeaProjects/Native/src/nativeInterop/cinterop/stat.def")
                packageName("libstat")
            }
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}
