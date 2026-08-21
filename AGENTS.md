# Task: Spring boot 프로젝트 생성

다음 스펙으로 Spring boot Maven 프로젝트 생성:

- Group: com.yonsai / Artifact: Day63_20260821
- Java 17 / Spring Boot 4.0.7
- Dependencies: web, devtools, thymeleaf, lombok, mysql, Spring Data JPA

curl.exe "https://start.spring.io/starter.zip?type=maven-project&groupId=com.yonsai&artifactId=Day63_20260821&name=Day63_20260821&javaVersion=17&bootVersion=4.0.7&dependencies=web,devtools,thymeleaf,lombok,mysql,data-jpa" -o project.zip

mkdir Day63_20260821

tar -xf project.zip -C Day63_20260821

rm project.zip

# 검증: Day63_20260821/pom.xml, Day63_20260821/src 존재 확인