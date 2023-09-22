plugins {
    id("java")
    id("com.avast.gradle.docker-compose") version "0.17.5"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework:spring-web:4.1.0.RELEASE")
    implementation("org.json:json:20230618")
    implementation("com.googlecode.json-simple:json-simple:1.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

}

dockerCompose {
    useComposeFiles.add("docker-compose.yml")
    isRequiredBy(tasks.test)
}

tasks.test {
    useJUnitPlatform()
}