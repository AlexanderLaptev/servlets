plugins {
    java
    war
}

group = "ru.vsu.cs.servlets"
version = "0.0.1"

tasks.withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.2")
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.eclipse.jetty:apache-jstl:11.0.0")
    implementation("com.google.code.gson:gson:2.13.1")
}
