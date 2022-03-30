# ItemService-project

---
## 목차

- [개발 기간](#기간)
- [개발 목적](#목적)
- [개발 환경](#환경)
- [사용 기술](#기술)
  + 백엔드
  + 프론트엔드
- [주요 기술](#주요)
- [패키징 구조](#패키지)
- [실행 화면](#화면)
- [시스템 아키텍처](#아케텍처)
- [다이어그램](#다이어그램)
- [핵심 기능](#핵심)
  + [로그인 & 로그아웃](#로그인)
  + [쿠키 생성](#쿠키)
  + [세션 관리](#세션)
  + [로그 출력](#로그)
  + [타임아웃](#타임아웃)
  + [Firebase DB 접근](#DB접근)
  + [아이템 상세 정보](#items)
  + [아이템 정보 수정](#edit)
- [보완할 점](#보완)


---

## <div id="기간">개발 기간</div>
- 22.03.20 ~



---

## <div id="목적">개발 목적</div> 
- 현재 동영상 강의와 검색을 통해 스프링 부트의 기능과 다른 것들을 배우고 있는 중에 있습니다. 하지만 가만히 보고 있는 것보다 직접 제가 손으로 작성하여 웹 사이트를 만들어보고 싶었습니다. 그리고 제가 배운 기술들을 적용해 보아서 실제 웹 사이트처럼 기능하고, 보여질 수 있게 하고싶습니다.
---

## <div id="환경">개발 환경</div>
- IntelliJ
- Postman
- Github
- Firebase


---

## <div id="tech">사용 기술</div>

### 주요 프레임워크/라이브러리

### 백엔드
+ Java 11 openjdk
 + 스프링 부트 2.6.0
 + Thymeleaf
 + Lombok

#### Database
  * Firebase



#### 프론트엔드
  + Javascript
  + HTML/CSS
  + Bootstrap
---

## <div id="주요">주요 기술</div>

* 개인으로 스프링 부트를 사용하여 웹 애플리케이션 전과정 개발, 경험, 운영

* MVC 프레임워크 기반 백엔드 서버 구축,

* 로그인 필터, 타임아웃

* 파이어베이스 연동,

---
## <div id="패키지">패키징 구조</div>

![img.png](img.png)![img_1.png](img_1.png)


---

## <div id="화면">실행 화면</div>

#### 1. 로그인
<img src="https://user-images.githubusercontent.com/79649052/160763454-735a8366-e715-46f9-8bc6-19ea2e8ea010.gif" width="400" height="250"/>


#### 2. 회원가입시 데베 추가
<img src="https://user-images.githubusercontent.com/79649052/160763390-64838d35-5f26-412b-a32c-38ebde6269e2.gif" width="400" height="250"/>


#### 3. 비로그인 상태에서 상품관리창 접근시 필터 작용
<img src="https://user-images.githubusercontent.com/79649052/160763471-80863390-e1f6-466b-a74a-5ad7540acebf.gif" width="400" height="250"/>


#### 4. 데베에서 불러온 리스트 출력과 반응형 웹
<img src="https://user-images.githubusercontent.com/79649052/160763508-f72f6b27-6616-4c08-9cf7-59e84e179eba.gif" width="400" height="250"/>


#### 5. 아이템 상품 수정시 수정 내용 데베 업데이트
<img src="https://user-images.githubusercontent.com/79649052/160763488-8d659b9e-e558-4b46-b450-b8f55bb75c5e.gif" width="400" height="250"/>


#### 6. 아이템 상품 추가시 데베 추가 상품 업데이트
<img src="https://user-images.githubusercontent.com/79649052/160763496-f28f91c9-9cd6-43c6-8591-00392d1bc2c0.gif" width="400" height="250"/>

---

## <div id="아키텍처">시스템 아키텍처</div>
<img width="577" alt="image" src="https://user-images.githubusercontent.com/79649052/160830171-4787b5fb-d9bf-4f36-8cd3-e8b975b00061.png">


---

## <div id="다이어그램">다이어그램</div>


---
## <div id="핵심">핵심 기능</div>

#### <div id="로그인">로그인 & 로그아웃</div>

#### <div id="쿠키">쿠키 관리</div>

#### <div id="세션">세션 관리</div>

#### <div id="로그">로그 출력</div>

#### <div id="타임아웃">타임아웃</div>

#### <div id="DB접근">Firebase DB 접근</div>

#### <div id="items">아이템 상세정보</div>

#### <div id="edit">아이템 정보 수정</div>


---

## <div id="보완">보완할 점</div>



---
