import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_19
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.7"
}

group = "com.severett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlinx.benchmark")

    tasks {
        withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(JVM_19)
            }
        }
        test {
            useJUnitPlatform()
        }
    }

    benchmark {
        targets {
            register("main") {
                this as JvmBenchmarkTarget
                jmhVersion = "1.36"
            }
        }
    }

    dependencies {
        val junitVersion: String by project

        implementation(kotlin("stdlib"))
        runtimeOnly("org.jetbrains.kotlinx:kotlinx-benchmark-runtime-jvm:0.4.6")

        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    }
}
