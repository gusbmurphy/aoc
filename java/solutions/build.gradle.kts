plugins {
    java
    id("info.solidsoft.pitest") version "1.19.0-rc.2"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

pitest {
    targetClasses.set(setOf("com.gusmurphy.fun.aoc.*"))
    verbose.set(true)
    junit5PluginVersion.set("1.2.3")
    pitestVersion.set("1.19.4")
}