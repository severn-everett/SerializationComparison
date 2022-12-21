plugins {
    kotlin("plugin.serialization") version "1.7.22"
}

group = "com.severett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}
