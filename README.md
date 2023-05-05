# devchyun96 /Board Project
스프링 기본 게시판입니다.


# 목차
**[프로젝트 소개](#프로젝트-소개)**

**[사용 기술](#사용-기술)**

**[구조 및 설계](#구조-및-설계)**

**[실행 부분](#실행-부분)**

**보완해야 할 점과 느낀점**





# 프로젝트 소개
**1. 프로젝트 기간 :2023/04/09~2023/05/05** 


**2. 프로젝트 기능 :**

    게시판- CRUD , 페이징, 검색, 조회수, 추천

    유저- OAuth2.0(구글 로그인, 네이버 로그인), Security 회원가입,회원가입 중복검사 및 유효성 검사

    댓글- CRUD



# 사용 기술


**1. backend :** 

    language : java 11
    
    framework : springboot 2.6.7
  
    library : spring data jpa / spring security / OAuth2.0 / validation / web / queryDsl / lombok
    
    build : Gradle 7.6.2
    
    Database : h2database 2.1.214 / jdbc / MySQL 8.0.31 community
    
    
    
**2. frontend :**

    HTML
    
    JS
    
    CSS
    
    Mustache
    
    bootStrap 4.3.1
    
    
    
# 구조 및 설계
**1. 패키지 구조**
<details>
<summary> 패키지 구조 </summary>

```
src
├─└main
│──├java
│──├─└BoardService
│──├─────└ board
│──├────────├ config 
│──├────────│──└JpaConfig        
│──├────────├ controller
│──├────────│──├ CommentApiContoller
│──├────────│──├ PostsApiController
│──├────────│──├ PostsIndexController
│──├────────│──├ UserApiController
│──├────────│──└ UserIndexController
│──├────────├ domain
│──├────────│──├ BaseTimeEntity
│──├────────│──├ Comment
│──├────────│──├ Posts
│──├────────│──├ Role
│──├────────│──└ User
│──├────────├ dto
│──├────────│─── commentdto
│──├────────│────├ CommentRequestDto
│──├────────│────└ CommentResponseDto
│──├────────│─── postsdto
│──├────────│────├ PostsListDto
│──├────────│────├ PostsResponseDto
│──├────────│────├ PostsSaveDto
│──├────────│────└ PostsUpdateDto
│──├────────│─── userdto
│──├────────│────├ UserRequestDto
│──├────────│────└ UserResponseDto
│──├────────├ repository
│──├────────│─── commentrespository
│──├────────│────└ CommentRepository
│──├────────│─── postsrepository
│──├────────│────├ PostsRepository
│──├────────│────├ PostsRepositoryCustom
│──├────────│────└ PostsRepositoryCustomImpl
│──├────────│─── userrepository
│──├────────│────└ UserRepository
│──├────────├ security
│──├────────│─── auth
│──├────────│────├ CustomAuthFailHandler
│──├────────│────├ CustomUserDetails
│──├────────│────├ CustomUserDetailService
│──├────────│────├ LoginUser
│──├────────│────└ LoginUserArgumentResolver
│──├────────│─── config
│──├────────│────├ SecurityConfig
│──├────────│────└ WebConfig
│──├────────│─── oauth
│──├────────│────├ CustomOAuth2UserService
│──├────────│────└ OAuthAttributes
│──├────────│─── validator
│──├────────│────├ AbstractValidator
│──├────────│────├ EmailValidate
│──├────────│────├ NicknameValidate
│──├────────│────└ UsernameValidate
│──├────────├ service
│──├────────│──├ CommentService
│──├────────│──├ PostsService
│──├────────│──└ UserService
│──├────────└ Application
│──├ resources
│──├── static
│──├─────├ css
│──├─────│──└ Board.css
│──├─────├ js
│──├─────│──└ app
│──├─────│─────└ index.js
│──├── templates
│──├─────├ comments
│──├─────├──├ commentForm.mustache
│──├─────├──└ commentList.mustache
│──├─────├ layout
│──├─────├──├ footer.mustache
│──├─────├──└ header.mustache
│──├─────├ posts
│──├─────├──├ postsSave.mustache
│──├─────├──├ postsSearch.mustache
│──├─────├──├ postsUpdate.mustache
│──├─────├──└ postsView.mustache
│──├─────└ users
│──├─────├──├ userJoin.mustache
│──├─────├──├ userLogin.mustache
│──├─────├──└ userUpdate.mustache
│──├─────└ index.mustache
│──├─ application.properties  
│──└─ application-oauth.properties
└ test
```
    
</details>


**2. DB 구조 및 설계**

![스키마 다이어그램](https://user-images.githubusercontent.com/74132326/236361155-83dd5b31-9c2d-4032-a8e9-ddf792540402.jpg)

![posts 테이블](https://user-images.githubusercontent.com/74132326/236364642-88e42b3e-d019-463a-aed6-6765cab7c653.jpg)

![users 테이블](https://user-images.githubusercontent.com/74132326/236364657-586597da-5955-43a9-80fd-a5cd2bde7acc.jpg)

![comments 테이블](https://user-images.githubusercontent.com/74132326/236364663-ca29944b-7d1c-41a7-bfba-f4eaf369eaac.jpg)

**3. API 설계**

****1. posts API****

| API URL | API method|  API 기능    |
| ------- | --------- | ----------------- |
|  ` / `  |  `GET`   |  게시글 목록 조회 |
|  `?page={pageNumber}` | `GET` | 게시글 페이징 | 
|  `/posts/save`| `GET` | 게시글 저장 페이지 |
|  `/posts/update/{id}` | `GET` | 게시글 수정 페이지 |
|  `/posts/view/{id}`   | `GET` | 게시글 보기 |
|  `/posts/search?keyword={keyword}` | `GET` | 게시글 검색 |
| `/posts/search?keyword={keyword}&page={pageNumber}` | `GET` | 게시글 검색 후 페이징 |
| `/api/v1/posts` | `POST` | 글 등록 |
| `/api/v1/posts/{id}` | `GET` | 게시글 조회 |
| `/api/v1/posts/{id}` | `PUT` | 게시글 수정 |
| `/api/v1/posts/{id}` | `DELETE` | 게시글 삭제 | 

****2. user API****

| API URL | API method|  API 기능    |
| ------- | --------- | ----------------- |
| `/auth/join`  | `GET`  | 회원 가입 페이지  |
| `/auth/joinProcedure`  | `POST` | 회원 가입 후 로그인 페이지 |
| `/auth/login` | `GET` | 로그인 페이지 |
| `/logout`| `GET` | 로그아웃 |
| `/userUpdate` | `GET` | 계정 정보 수정 페이지 |
| `/api/v1/users` | `PUT` | 계정 정보 수정 |

****3. comment API****

| API URL | API method|  API 기능    |
| ------- | --------- | ----------------- |
| `/api/v1/posts/{id}/comments` | `POST` | 댓글 등록 |
| `/api/v1/posts/{id}/comments/{commentId}` | `PUT` | 댓글 수정 |
| `/api/v1/posts/{id}/comments/{commentId}` | `DELETE` | 댓글 삭제 |

# 실행 부분

    
**1. 게시판 화면**

<details>
<summary>게시판 화면</summary>

![navbar](https://user-images.githubusercontent.com/74132326/236385833-f3a3e1b2-948b-4fd1-8770-715044513c6f.jpg)

![table](https://user-images.githubusercontent.com/74132326/236385849-284f571a-2a41-4c93-bec8-b5e2c8283604.jpg)

![page and search](https://user-images.githubusercontent.com/74132326/236385858-8326f124-0d36-4f20-8261-67a44f8419d0.jpg)
    
</details>    

**2. 게시글 등록**

<details>
<summary>게시글 등록</summary>


게시글 등록 창 
![게시글 등록 창](https://user-images.githubusercontent.com/74132326/236386043-da25f551-b3b8-4b00-8a2f-5cd5977052a8.jpg)

등록 버튼을 누르고 정상적일 때 나오는 alert

![등록 버튼을 누른 후](https://user-images.githubusercontent.com/74132326/236386135-9f63b100-abb0-43bf-871e-e4f0babf54fc.jpg)

게시글이 등록 완료되어 테이블에 추가
![게시글 등록 완료](https://user-images.githubusercontent.com/74132326/236386440-42494daf-02f6-4ab1-86cb-af678cc29fe9.jpg)

링크를 타고 들어가면 나오는 게시글 view 

![게시글 view](https://user-images.githubusercontent.com/74132326/236386593-fb2c7635-c558-4b64-9b1d-20df507672c8.jpg)
    
 </details>


**3. 게시글 수정**

<details>
<summary>게시글 수정</summary>

게시글 수정

![게시글 update](https://user-images.githubusercontent.com/74132326/236386735-6ca238f2-f10f-4fce-9b65-91ca5abd11a4.jpg)

게시글 수정 후 

![게시글 update 후](https://user-images.githubusercontent.com/74132326/236386875-b09f6674-085c-4b38-becf-de05481b162d.jpg)
    
</details>

**4. 게시글 삭제**

<details>
<summary>게시글 삭제</summary>

게시글 삭제 
    
![게시글 delete](https://user-images.githubusercontent.com/74132326/236387575-d8d23251-0fdf-424f-a568-382468bc2c82.jpg)

게시글 삭제 후 게시판
    
![게시글 delete 완료](https://user-images.githubusercontent.com/74132326/236387624-1258b453-b583-48e3-b70f-a41a8e9fc01a.jpg)

</details>

**5. 회원가입**

<details>
    <summary>회원가입 </summary>

회원가입 중복검사 및 유효성 검사

![회원가입 form](https://user-images.githubusercontent.com/74132326/236388055-25cba6e6-2b4c-406f-8a37-ce6598c083d5.jpg)
    
    
</details>
    




    












    

    
