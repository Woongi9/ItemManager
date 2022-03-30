# ItemService-project

---
## 목차

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
  + [서블릿 필터](#필터)
  + [타임아웃](#타임아웃)
  + [Firebase DB 접근](#DB접근)
  + [타임리프, 체크박스, 라디오 버튼, 셀렉트 박스](#items)
- [프로젝트를 통해 느낀점](#느낀점)


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

![img](https://user-images.githubusercontent.com/79649052/160853661-8e0350e5-452b-4bba-8370-7798781654b2.png)
![img_1](https://user-images.githubusercontent.com/79649052/160853691-f06cfa6a-dae9-4d3f-87e3-3c4b0d6d0f83.png)


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

### <div id="로그인">로그인 & 로그아웃</div>
- 파이어베이스에 저장되어있는 회원을 불러와 MemberRepository에 저장시킨 다음에 파라미터로 넘어온 password와 비교해서 같으면 회원을 반환하고, 만약 password가 다르면 null을 반환한다.(보안을 위해 세션 쿠키를 사용)
 <img width="468" alt="image" src="https://user-images.githubusercontent.com/79649052/160853513-5c150ebf-599e-4cf0-b7d8-7c0c94845038.png">

- 로그아웃시 LoginController에서 세션 쿠키이므로 웹 브라우저 종료시 서버에서 해당 쿠키의 종료 날짜를 0으로 지정하는 expireCookie함수를 사용한다.
  <img width="468" alt="image" src="https://user-images.githubusercontent.com/79649052/160855602-3c9d730f-84fd-418a-a0a3-8de3ef05dbfc.png">

### <div id="쿠키">쿠키 관리</div>
- 쿼리 파라미터를 계속 유지하면서 보내는 것은 매우 어렵고 번거로운 작업이기에 쿠키를 사용한다. 브라우저를 종료시 까지만 유지하므로 세션 쿠키를 사용한다. 로그인 쿠키가 없는 사용자는 기존 home 화면으로 보낸다. 로그인 쿠키가 있는 사용자는 로그인 사용자 전용 홈 화면인 loginHome으로 보낸다.

### <div id="세션">세션 관리</div>
- 쿠키 값은 변경이 가능하므로 보관된 정보를 외부인이 쉽게 가져갈 수 있는 문제가 있기에 쿠키에 중요 값을 노출하지 않고, 임의의 토큰을 노출하는 방법인 세션 쿠키를 사용한다.
  + loginId, password 정보를 전달하면 서버에서 해당 사용자가 맞는지 확인한다.
  
<img width="468" alt="image" src="https://user-images.githubusercontent.com/79649052/160859285-fff3b758-9c17-4d25-9555-08edaaa3db15.png">
  + 세션 ID를 생성하는데 randomUUID를 사용하여 임의의 값을 쿠키 값으로 가진다.
  

- _서버는 클라이언트에 mySessionId라는 이름으로 세션ID만 쿠키에 담아서 전달한고 클라이언트는 쿠키 저장소에 mySessionId 쿠키를 보관한다._

#### 회원과 관련된 정보는 클라이언트에 전달하지 않고, 오직 추정 불가능한 세션 ID만 쿠키를 통해 클라이언트로 전달된다.
<img width="468" alt="image" src="https://user-images.githubusercontent.com/79649052/160859712-0156635b-43a7-45d2-87fd-7039a516fc0f.png">

- SessionManager에 세션 생성, 조회, 삭제 구현



### <div id="타임아웃">타임아웃</div>

- 세션은 사용자가 로그아웃을 직접 호출해서 삭제되지만 대부분의 사용자는 로그아웃을 하지않고 웹브라우저를 종료한다. 따라서 세션 데이터를 종료한 것인지 아닌지를 인식할 수 없기 때문에 세션 타임아웃을 스프링 부트로 글로벌 설정하는 방법을 사용하였다. 
 application.properties 에서 server.servlet.session.timeout=1800 으로 30분을 설정하였다. session.getLastAccessedTime() 으로 최근 세션 접근 시간을 찾고, LastAccessedTime이후로 timeout시간이 지나면, WAS가 내부 해당 세션을 제거하는 방법을 사용하였다.  


### <div id="필터">서블릿 필터</div>
- 로그인한 사용자만 상품 관리 페이지에 들어갈 수 있도록 서블릿 필터를 사용

| HTTP 요청 -> WAS -> 필터 -> 서블릿 -> 컨트롤러   //로그인 사용자

| HTTP 요청 -> WAS -> 필터 (서블릿 호출 안됨)  //비로그인 사용자

<img width="500" alt="image" src="https://user-images.githubusercontent.com/79649052/160862214-8c67c635-d589-4f2e-8c1f-8e5c1f8ef686.png">

- HTTP 요청 오면 doFIlter 호출 (ServletRequest request는 HTTP 요청이 아닌 경우까지 고려해서 만든 인터페이스)
- chain.doFilter(request, response);
  - 다음 필터가 있으면 필터 호출, 없으면 서블릿 호출
- 요청 로그 기록

<img width="500" alt="image" src="https://user-images.githubusercontent.com/79649052/160863956-db9cc8cf-712a-4587-a08f-ee5ea87d03fe.png">

- LoginCheckFilter에서 whitelist 배열은 로그인이 되지 않아도 접근 가능하도록 항상 허용 배열이다. isLoginCheckPath(requestURI)를 통해 나머지 모든 경로에는 인증 체크 로직을 적용한다.

<img width="500" alt="image" src="https://user-images.githubusercontent.com/79649052/160865131-0150d6e8-93bc-4978-92d3-1ca07f7581af.png">

- WebConfig에서 filterRegistrationBean.setOrder() 를 통해 로그 필터가 먼저 후에 로그인 체크 필터 순으로 설정한다.



### <div id="DB접근">Firebase DB 접근</div>

- Firebase DB에 저장되어있는 컬렉션 별로 Service 인터페이스를 생성한다.
- Firebase의 문서의 ID는 모두 숫자형으로 저장되어있다.

- 로그인 화면에 처음 갔을 경우, 상품관리를 처음에 들어갔을 시에만 반복문으로 Repository에 저장한다. (후에는 Repository를 가지고 목록을 출력시켜 시간을 단축시킨다.)
- 데이터 추가시에는 Firebase에 같이 추가해주면서 Firebase의 문서ID 값은 멤버나 아이템의 id 값으로 저장한다. 
- 아이템 정보 변경시에도 Firebase DB에 업데이트 해준다.


### <div id="items">타임리프, 체크박스, 라디오 버튼, 셀렉트 박스</div>

<img width="468" alt="image" src="https://user-images.githubusercontent.com/79649052/160874349-fab24b5a-4207-4909-853b-0f6a0b845ccf.png">

- 아이템 상세정보 창과 수정 창에서 타임리프의 기능을 사용하여 사용자가 입력한 정보 타임리프 기능을 통해 데이터 저장, 출력, 입력 하는데 사용
- 아이템 창 뿐만 회원 등록, 로그인에서 아닌 다양한 부분에서 타임리프 사용 


---

## <div id="느낀점">프로젝트를 통해 느낀점</div>



---
