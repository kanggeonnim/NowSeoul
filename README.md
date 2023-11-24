# 🚩 NOW SEOUL
> 지금 서울의 실시간 상황 데이터를 이용한 지도 웹 페이지
>
## ✅ Demo
> When clicking the image, you can watch the demonstration video
>
> [![Video Label](http://img.youtube.com/vi/vvC-j8gSpkA/0.jpg)](https://youtu.be/vvC-j8gSpkA)
>
### WatchDemo and Follow
> 지도의 마커 클릭시 해당 지역의 실시간 정보 제공
> 게시판을 통한 글 작성, 수정, 삭제
> 카카오 로그인
> 일부 클릭시 3d 모델링을 통한 T1 League of legend world chapionship 우승 축하
> 마이페이지에 있는 문의하기를 통한 관리자와 채팅 
>
## 🛠 Development Environment
> - OS: Windows 10 (or MacOS)
> - JavaScript-Runtime: Chrome (ver. 119.0.6045.160)
> - Code Editor: Visual Studio Code, STS (ver. 3.9.14) 
> - Code Convention: ESLint, Prettier,Pinia,Axios
> - Framework: vue.js (ver. 3.3.4), spring boot (ver. 2.7.17)
> - Kakao Login API
> - Kakao Map API
> - SeoulSity data
> - Web Socket
## ⚙ Production Environment
> - Vite: ver. 4.4.11
> - JavaScript-Runtime: Chrome (ver. 119.0.6045.160)
> - Git: ver. 2.34.1
>
## 📕 Guide Line
> - ### FOR USERS
> - 1. WebPage site connect
> - 2. login and memberjoin
> - 3. main page map 
> - 4. board page
> - 5. mypage admin question

## 🎨 전체기능
> ### Back에서 서울시 실시간 공공 데이터를 가져온다.
> - back에서 spring boot mvc 패턴을 이용하여 서울시 공공 데이터 api를 5분 마다 호출하여 DB에 저장한다.
> ### 로그인시 카카오 로그인 API를 이용하여 로그인이 가능하다.
> <img src="https://github.com/kanggeonnim/NowSeoul/assets/104503792/a7869942-cf80-4c8f-8d87-dd328aae3729" width="50%" height="50%"/>

> - 카카오를 통해 OAuth 2.0 인증을 사용하여 사용자 로그인을 관리한다.
> ### 게시판을 통한 유저간의 소통 활성화가 가능하다.
> - CRUD를 활용한 게시판의 기본기능을 제공한다.
> ### 마이페이지에 문의하기 버튼을 통해 유저와 관리자간의 채팅이 가능하다.
> - spring boot에 서버를 만들고 websocket을 이용하여 채팅 기능이 가능하다.
> ### 메인 페이지에 있는 맵을 이용하여 서울의 실시간 데이터를 얻을 수 있다.
> - kakao map api에 있는 맵생성 마크 구현 커스텀 오버레이를 이용하여 화면 구성 전체적인 데이터는 axios를 이용하여 DB에있는 데이터를 가져오고 이미지 파일은 서울시 실시간 데이터 사이트에서 호출하여 가져온다 
> ### 일부 버튼 클릭으로 3D모델링을 감상할 수 있다.
> - three를 이용하여 vue에 만들어진 3D모델링을 애니메이션화 가능하다.
>

### 유스케이스 다이어그램
<img src="https://github.com/kanggeonnim/NowSeoul/assets/104503792/5e9d0be0-6ec1-4770-aa3c-6d20c940e8c5" width="80%" height="80%"/>

### ERD
<img src="https://github.com/kanggeonnim/NowSeoul/assets/104503792/f6c589d0-73f2-4291-89ae-672de96757df" width="80%" height="80%"/>

### 클래스 다이어그램
<img src="https://github.com/kanggeonnim/NowSeoul/assets/104503792/4a150c75-bae4-45f3-85f9-71cce0332ff7" width="80%" height="80%"/>


# NowSeoul
## 강건
> - Back Developer 
> - spring boot scheduler cron을 통한 DB 갱신 
> - board page CRUD develop
> - kakao token authorization & jwt token generate
## 백현우
> - Front Developer
> - kakao map api를 통한 마커 생성 및 실시간 정보 표시
> - seoul sity data api를 통한 data 갱신
## 전현철
> - Full stack Developer
> - gooogle and naver login develop 
> - web socket using chatting funcion develop (admin, client)
> - three funtion using 3d modeling develop

