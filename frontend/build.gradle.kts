plugins {
    id("org.siouan.frontend") version "2.0.0"
    id("java")
}

group = "com.kotlin-spring-react"
version = "0.0.1-SNAPSHOT"

java {
    targetCompatibility = JavaVersion.VERSION_1_8
}

buildscript {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

frontend {
    nodeDistributionProvided.set(true)
    nodeVersion.set("10.16.0")
    cleanScript.set("run clean")
    installScript.set("install")
    assembleScript.set("run build")
}

tasks.named("jar", Jar::class) {
    dependsOn("assembleFrontend")
    from("$buildDir")
    into("static")
}