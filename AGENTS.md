# Task: Spring boot 프로젝트 생성

다음 스펙으로 Spring boot Maven 프로젝트 생성:
- Group: com.yonsai / Artifact: Day59_20260814
- Java 17 / Spring Boot 4.0.7
- Dependencies: web, devtools

```powershell
curl.exe "https://start.spring.io/starter.zip?type=maven-project&groupId=com.yonsai&artifactId=Day59_20260814&name=Day59_20260814&javaVersion=17&bootVersion=4.0.7&dependencies=web,devtools" -o project.zip
mkdir Day59_20260814
tar -xf project.zip -C Day59_20260814
rm project.zip
```

**검증**: `Day59_20260814/pom.xml`, `Day59_20260814/src` 존재 확인