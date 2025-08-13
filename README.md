# tre-library

## 프로젝트 설명

> Tre Library는 도서 검색 및 관리 API 서버입니다. 사용자는 도서를 키워드로 검색(단일/복합)하거나, 상세 정보를 조회할 수 있습니다.

<br>

## 도메인 모델 설명

### 도서 (Book)

| <div style="width:200px"> **필드** | <div style="width:50px">           **타입** | <div style="width:290px"> **설명** |
|:--------------------------------:|:-----------------------------------------:|:--------------------------------:|
|                id                |                   Long                    |              고유 식별자              |
|              title               |                  String                   |                제목                |
|             subTitle             |                  String                   |               부제목                |
|              author              |                  String                   |                작가                |
|            published             |                  String                   |               출판일                |
|               isbn               |                  String                   |           국제 표준 도서 번호            |
|             imageUrl             |                  String                   |             이미지 URL              |
|            createdAt             |               LocalDateTime               |               생성일                |
|          lastModifiedAt          |               LocalDateTime               |             마지막 수정일              |

### 인기 검색어 (PopularSearchTerm)

| <div style="width:200px"> **필드** | <div style="width:50px">           **타입** | <div style="width:290px"> **설명** |
|:--------------------------------:|:-----------------------------------------:|:--------------------------------:|
|                id                |                   Long                    |              고유 식별자              |
|               term               |                  String                   |                용어                |
|              count               |                   Long                    |               검색 수               |
|            createdAt             |               LocalDateTime               |               생성일                |
|          lastModifiedAt          |               LocalDateTime               |             마지막 수정일              |

<br>

## 실행 방법

```
docker-compose up --build
```

- `http://localhost:8080` 접속

<br>

## API 문서

### 도서 (Book)

| <div style="width:200px"> **Method** |       <div style="width:350px">           **URI**       | <div style="width:290px"> **Description** |
|:------------------------------------:|:-------------------------------------------------------:|:-----------------------------------------:|
|                 GET                  |                   /api/v1/books/{id}                    |                 도서 상세 조회                  |
|                 GET                  | /api/v1/books?keyword={keyword}&page={page}&size={size} |                 도서 단일 검색                  |
|                 GET                  |   /api/v1/books/search?q={q}&page={page}&size={size}    |                 도서 복합 검색                  |

### 인기 검색어 (PopularSearchTerm)

| <div style="width:200px"> **Method** | <div style="width:350px">           **URI** | <div style="width:290px"> **Description** |
|:------------------------------------:|:-------------------------------------------:|:-----------------------------------------:|
|                 GET                  |     /api/v1/popular-search-terms/top10      |            인기 검색어 상위 10 목록 조회             |

<br>

## 기술 스택 및 선택 이유

- Java : 오랜 기간 널리 사용되어 검증된 언어입니다. 또한, 객체 지향 프로그래밍에 최적화되어 확장이 용이합니다.
- JPA + QueryDSL : JPA를 이용하면 객체 지향적으로 데이터베이스 매핑(ORM)이 가능합니다. QueryDSL은 동적인 검색 조건을 처리하기 위해 사용하였습니다.
- MySQL : 안정적이고 오랜 기간 검증된 오픈소스 RDBMS입니다.
- Docker Compose : 여러 컨테이너(앱, DB 등) 환경을 한 번에 구성 가능합니다. 또한, 로컬과 배포 환경에서 동일한 실행 환경을 재현하기 위해 사용하였습니다.

<br>

## 아키텍처 결정 사항

### 패키지 구조

- `books`
    - `application` : 비즈니스 로직(Service) 구현
    - `domain` : 엔티티, 리포지토리 인터페이스
    - `presentation` : API Controller
- `core`
    - `config` : 전역 설정
    - `domain` : `BaseEntity`와 같은 공통 엔티티 상속 구조
    - `exception` : 전역 예외 처리 및 커스텀 예외 정의
    - `response` : 공통 응답 객체(`SimplePage`)
    - `utils` : Util 클래스
- `popularSearchTerms`
    - `application` : 인기 검색어 관련 비즈니스 로직(Service)
    - `domain` : 엔티티, 리포지토리
    - `presentation` : API Controller

일반적인 3 Tier Architecture를 기반으로 하되,
Service는 비지니스 흐름만 제어하고 세부 구현은 Reader로 분리하였습니다.
<br>
이를 통해 각 기능의 역할이 명확해져 테스트와 유지보수가 용이합니다.

<br>

## 문제 해결 중 고민 과정

1. 현재는 키워드가 최대 2개를 받아 검색을 수행하지만, 추후 n개의 키워드를 받으면 JPA로는 이를 대응하기 어렵습니다.
   <br>
   때문에 미래의 확장 가능성을 열어 두기 위해 QueryDSL을 적용하였습니다. QueryDSL을 이용하면 동적으로 검색 조건을 추가/제외하기 용이하기 때문입니다.


2. 복합 검색에 사용되는 `|` 또는 `-`에 대해서 어떻게 파싱해서 결과를 가져올지 고민되었습니다.
   <br>
   자바에 특정 문자를 기반으로 배열을 반환하는 split 함수가 존재한다는 것이 기억났고, 이를 이용하여 입력받은 문자를 배열로 반환한 뒤, QueryDSL로 조건을 처리하였습니다. 
   이 과정에서 `|` 또는 `-`가 섞여 있거나 1개 이상일 때 예외를 반환하도록 처리하였습니다.