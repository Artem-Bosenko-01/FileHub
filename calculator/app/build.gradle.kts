/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.0/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter API for testing.
    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-log4j12:1.7.5")
    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.0-jre")
}

application {
    // Define the main class for the application.
    mainClass.set("com.teamdev.calculator.Main")
}

tasks.test {
    // Use junit platform for unit tests.
    useJUnitPlatform()
}
