plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.javalin:javalin-bundle:6.6.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testImplementation("org.mockito:mockito-core:5.+")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")
    testImplementation("io.javalin:javalin-testtools:5.6.1")
// Use the latest stable version
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0")
// Use the latest stable version
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.0")
// Crucial for Gradle
}

tasks.test {
    useJUnitPlatform()
}