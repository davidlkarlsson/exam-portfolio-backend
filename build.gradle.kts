plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.david.examportfolio"
version = "0.0.1-SNAPSHOT"
description = "Backend for my developer portfolio. Provides REST API for projects, skills, and contact form with PostgreSQL database and SendGrid email integration."

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testCompileOnly("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.resend:resend-java:3.1.0")
    // https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
    implementation ("io.jsonwebtoken:jjwt-api:0.12.6" )
    implementation ("io.jsonwebtoken:jjwt-impl:0.12.6" )
    implementation ("io.jsonwebtoken:jjwt-jackson:0.12.6" )
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
