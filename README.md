# 기억담아 Backend

가족 구성원이 추억, 일정, 앨범, 게시글을 함께 기록하고 공유할 수 있는 가족 커뮤니티 앱 **기억담아**의 백엔드 서버입니다.

본 프로젝트는 가족 간 소통과 기록 보관을 돕기 위해 기획되었으며, 사용자가 가족방을 생성하고 가족 구성원과 함께 게시글, 앨범, 일정, 추억을 관리할 수 있도록 지원합니다.

## 프로젝트 개요

**기억담아**는 가족 구성원이 함께 사용하는 커뮤니티 서비스입니다.
가족 단위의 기록을 한 공간에 모으고, 일정과 추억을 공유하며, 사진과 게시글을 통해 세대 간 소통을 돕는 것을 목표로 했습니다.

백엔드 서버는 Android 클라이언트와 REST API 방식으로 통신하며, 사용자 정보, 가족방, 게시글, 댓글, 앨범, 일정, 이미지 업로드 등의 데이터를 관리합니다.

## 주요 기능

* 사용자 회원 관리
* 가족방 생성 및 참여
* 게시글 작성, 조회, 수정, 삭제
* 댓글 작성 및 조회
* 앨범 및 이미지 관리
* 일정 관리
* Android 클라이언트 연동을 위한 REST API 제공
* AWS S3 기반 이미지 저장
* MySQL 기반 데이터 관리

## 기술 스택

### Backend

* Java 17
* Spring Boot 3.2.10
* Spring Web
* Spring Data JPA
* Spring JDBC
* Gradle

### Database

* MySQL
* H2 Database

### Infra / Storage

* AWS S3

### Client

* Android Java
* Retrofit
* Gson Converter
* Kakao Login
* Naver Map API

## 관련 저장소

* Backend Repository: https://github.com/hyemin22/CapstoneDesignSpringServer
* Android Repository: https://github.com/hyemin22/CapstoneDesign

## 프로젝트 구조

```text
CapstoneDesignSpringServer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── build.gradle
├── settings.gradle
├── gradlew
└── gradlew.bat
```

## 담당 역할

본 프로젝트에서 백엔드 개발을 중심으로 다음 역할을 수행했습니다.

* 요구사항 분석
* ERD 설계
* API 설계 및 구현
* JPA 기반 데이터 처리
* 게시글, 앨범, 일정 등 주요 도메인 기능 구현
* AWS S3 이미지 업로드 기능 연동
* Android 클라이언트와 API 연동
* 데이터 삭제 정책 및 테이블 관계 검토

## 주요 경험

### 1. ERD 및 API 설계

가족 커뮤니티 서비스의 핵심 데이터인 사용자, 가족방, 게시글, 댓글, 앨범, 일정 등의 관계를 정의하고 ERD를 설계했습니다.
Android 클라이언트에서 필요한 화면 흐름을 기준으로 API를 설계하고, 각 기능에서 필요한 요청·응답 데이터를 정리했습니다.

### 2. 데이터 생명주기와 삭제 정책 개선

개발 과정에서 게시글 삭제 시 앨범 데이터까지 함께 삭제되는 문제가 발생했습니다.
처음에는 단순 삭제 API 오류처럼 보였지만, 원인을 분석한 결과 테이블 간 FK cascade 설정으로 인해 게시글과 앨범 데이터의 생명주기가 의도와 다르게 연결되어 있었습니다.

이를 해결하기 위해 게시글과 앨범이 서비스 내에서 어떤 관계를 가져야 하는지 다시 검토했습니다.
두 데이터가 화면상 함께 노출되더라도, 서비스 정책상 삭제되어야 하는 범위는 다르다고 판단했고, 게시글 삭제와 앨범 삭제 정책을 분리했습니다.

이 경험을 통해 백엔드 개발에서는 API 구현뿐 아니라 데이터의 생성, 수정, 삭제 기준과 테이블 관계를 함께 설계해야 한다는 점을 배웠습니다.

### 3. Android 클라이언트와의 연동

Android 앱에서 필요한 기능을 기준으로 API를 설계하고, Retrofit 기반 통신 흐름에 맞춰 서버 응답 구조를 구성했습니다.
클라이언트 화면에서 필요한 데이터와 서버에서 관리해야 하는 데이터를 함께 고려하며 개발했습니다.

## 실행 방법

### 1. 저장소 클론

```bash
git clone https://github.com/hyemin22/CapstoneDesignSpringServer.git
cd CapstoneDesignSpringServer
```

### 2. 환경 설정

`src/main/resources/application.properties` 또는 `application.yml`에 데이터베이스 및 AWS S3 관련 설정을 추가해야 합니다.

예시:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gieokdama
spring.datasource.username=your_username
spring.datasource.password=your_password

cloud.aws.credentials.access-key=your_access_key
cloud.aws.credentials.secret-key=your_secret_key
cloud.aws.s3.bucket=your_bucket_name
cloud.aws.region.static=ap-northeast-2
```

실제 운영 키와 비밀번호는 GitHub에 업로드하지 않아야 합니다.

### 3. 실행

```bash
./gradlew bootRun
```

Windows 환경에서는 다음 명령어를 사용할 수 있습니다.

```bash
gradlew.bat bootRun
```

## 성과

* 한국멀티미디어학회 2024 추계학술대회 Best Paper Award 수상
* 가족 커뮤니티 앱 저작권 등록
* Android 앱과 Spring Boot 서버를 연동한 팀 프로젝트 완성
* 요구사항 분석부터 ERD, API, 구현, 배포까지 백엔드 개발 전반 경험
