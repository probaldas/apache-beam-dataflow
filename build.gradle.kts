plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.probal"
version = "1.0-SNAPSHOT"

val beamVersion = "2.60.0"
val gcpCloudVersion = "2.22.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.beam:beam-sdks-java-core:$beamVersion")
    implementation("org.apache.beam:beam-sdks-java-io-google-cloud-platform:$beamVersion")
    implementation("org.apache.beam:beam-runners-google-cloud-dataflow-java:$beamVersion")
    implementation("com.google.cloud:google-cloud-storage:$gcpCloudVersion")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    jar {
        enabled = false // Disable default JAR to avoid conflict with Shadow JAR
    }

    shadowJar {
        archiveClassifier.set("") // Ensures the fat JAR does not have "-all" in its name
        isZip64 = true // Enable ZIP64 format to support archives with more than 65535 entries
        mergeServiceFiles() // Handle `META-INF/services` merging for services
        exclude("META-INF/LICENSE", "META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA") // Exclude unnecessary files
    }

    test {
        useJUnitPlatform()
    }
}

tasks.build {
    dependsOn(tasks.shadowJar) // Ensure the shadow JAR task runs as part of the build
}