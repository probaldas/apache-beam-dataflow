plugins {
    id("java")
}

group = "dev.probal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.beam:beam-sdks-java-core:2.61.0")
    implementation("org.apache.beam:beam-sdks-java-io-google-cloud-platform:2.61.0")
    implementation("org.apache.beam:beam-runners-google-cloud-dataflow-java:2.61.0")
    implementation("com.google.cloud:google-cloud-storage:2.22.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}