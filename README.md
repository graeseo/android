# graeseo-android

> 그래서 — Android 네이티브 앱 (Jetpack Compose)

바텀 네비게이션 등 네이티브 쉘 + 메인피드는 WebView(graeseo-web)로 로드.

## 기술 스택

- Kotlin + Jetpack Compose
- Hilt (DI)
- JUnit 5 + Mockk + Turbine (테스트)

## 브랜치 전략 (Git Flow)

```
main      ← 프로덕션 배포
develop   ← 통합 브랜치
feature/* ← 기능 개발
release/* ← 릴리즈 준비
hotfix/*  ← 긴급 수정
```

## 시작하기

Android Studio에서 열고 에뮬레이터 실행.

## 테스트

```bash
./gradlew testDebugUnitTest
```