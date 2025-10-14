# JWT Authorization with OAuth2

> Spring Boot application demonstrating JWT-based authentication with OAuth2 Resource Server supporting both reactive and non-reactive implementations.

## Overview

A flexible JWT authentication system built with Spring Security OAuth2 Resource Server. Supports both traditional servlet-based (Spring MVC) and reactive (Spring WebFlux) architectures with RSA-based JWT signing and validation.

**Key Features:**
- JWT token generation with RSA (RS256) algorithm
- OAuth2 Resource Server implementation
- Dual support: Reactive (WebFlux) and Non-Reactive (MVC)
- Stateless authentication with public/private key pairs
- Configurable token expiration and claims
- Nimbus JOSE JWT library integration
---

## Architecture
<img width="1154" height="976" alt="Mermaid Chart - Create complex, visual diagrams with text -2025-10-14-062006" src="https://github.com/user-attachments/assets/8a55bbce-3ea8-4edf-b264-a42da70cf59a" />

## Tech Stack

| Category | Technologies |
|----------|-------------|
| **Core** | Java 21, Spring Boot 3.2.0 |
| **Security** | Spring Security OAuth2 Resource Server |
| **Web** | Spring MVC / Spring WebFlux (switchable) |
| **JWT** | Nimbus JOSE JWT |
| **Algorithm** | RSA (RS256) |

## Getting Started
#### Prerequisites

```bash
- Java 21+
- Maven 3.8+
```

#### Installation

1. Clone and Build
```bash
git clone <repository-url>
cd jwt-authorization-oauth2
mvn clean install
```
2. Choose Architecture
The application supports two modes.
Configure by commenting/uncommenting dependencies in pom.xml:

##### For Non-Reactive (Spring MVC):
```xml
<!-- Keep this -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
```xml
<!-- Comment this -->
<!--
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
-->
```

Also comment out @Configuration in ReactiveWebSecurityConfig.java
#### For Reactive (Spring WebFlux):
```xml
<!-- Comment this -->
<!--
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
-->
```
```xml
<!-- Keep this -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```
Also comment out @Configuration in WebSecurityConfig.java

3. Configure Keys
Update application.yml with your RSA key pair:

```yaml
spring:
  security:
    jwt:
      publicKey: |
        -----BEGIN PUBLIC KEY-----
        MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA...
        -----END PUBLIC KEY-----
      privateKey: |
        -----BEGIN PRIVATE KEY-----
        MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcWW...
        -----END PRIVATE KEY-----
      issuer: localhost:8080
      algorithm: RS256
      expirationTime: 10m
```

5. Run Application
```bash
mvn spring-boot:run
```
Application starts on http://localhost:8080

## Related Articles

For a detailed step-by-step guide, check out my Medium article:

[JWT Authorization With Spring Security 6](https://levelup.gitconnected.com/jwt-authorization-with-spring-boot-3-and-spring-security-6-69dfd11321ff)

