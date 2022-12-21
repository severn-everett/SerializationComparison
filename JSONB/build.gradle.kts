group = "com.severett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.json:jakarta.json-api:2.1.1")
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.0")
    runtimeOnly("org.eclipse:yasson:3.0.2")
}
