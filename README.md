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

	
```mustache
    {{#posts}}
    <tr>
        <td>{{id}}</td>
        <td><a href="/posts/view/{{id}}">{{title}}</a></td>
        <td>{{author}}</td>
        <td>{{view}}</td>
        <td>{{recommend}}</td>
        <td>{{createdDate}}</td>
    </tr>
    {{/posts}}
```

![page and search](https://user-images.githubusercontent.com/74132326/236385858-8326f124-0d36-4f20-8261-67a44f8419d0.jpg)

search
```mustache
<form action="/posts/search" method="GET" class="form-inline p-2 bd-highlight" role="search">
    <div class="input-group mb-3">
    <input type="text" name="keyword" class="form-control" id="search">
    <button class="btn btn-outline-secondary "type="submit">검색</button>
    </div>
</form>
```	
    
 repository
 ```java 
    public List<Posts> findAllDesc() {
        return queryFactory // queryDsl을 사용
                .selectFrom(posts)
                .orderBy(posts.id.desc()) //orderBy 조건절에 id 내림차순으로 조회
                .fetch();
    }
 ```  

Service	
```java
    @Transactional(readOnly = true)
    public List<PostsListDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListDto::new)
                .collect(Collectors.toList()); //lambda 사용
    }
```
	

controller
 ```java
    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable,
                        @LoginUser UserResponseDto user) {
        Page<Posts> posts=postsService.page(pageable);
        if(user != null) {
	        model.addAttribute("users", user); // user가 있을 경우 user의 로그인 정보를 불러옴
        }
        model.addAttribute("posts", posts); //페이징 된 게시글을 불러옴 
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber()); //전 페이지의 숫자를 불러옴
        model.addAttribute("next", pageable.next().getPageNumber()); //다음 페이지의 숫자를 불러옴
        model.addAttribute("hasNext", posts.hasNext()); // 다음 페이지가 있는 경우
        model.addAttribute("hasPrev", posts.hasPrevious()); // 전 페이지가 있는 경우
        model.addAttribute("currentPage", pageable.getPageNumber()+1); // 페이지는 0부터 시작하기 때문에 +1
        return "index";
    }
```
	
mustache
```mustache
<nav aria-label="Page navigation ">
    <ul class="pagination justify-content-center">
	{{! 전 페이지가 있을 경우}}
        {{#hasPrev}}
            <li class="page-item">
                <a class="page-link" href="?page={{prev}}">
                <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        {{/hasPrev}}
	{{! 전 페이지가 없는 경우}}
        {{^hasPrev}}
            <li class="page-item disabled"> //사용하지 못하게 막음
                <a class="page-link" href="?page={{prev}}">
                <span aria-hidden="true" >&laquo;</span>
                </a>
            </li>
        {{/hasPrev}}
	{{! 현재 페이지의 넘버 }}    
        <li class="page-item disabled"> 
            <a class="page-link" href="?page={{currentPage}}">{{currentPage}}</a> 
        </li>
	{{! 다음 페이지가 있는 경우}}
        {{#hasNext}}
            <li class="page-item">
                <a class="page-link" href="?page={{next}}">
                <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        {{/hasNext}}
	{{ ! 다음 페이지가 없는 경우}}
        {{^hasNext}}
            <li class="page-item disabled" > // 사용하지 못하게 막음
                <a class="page-link" href="?page={{next}}">
                <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        {{/hasNext}}
    </ul>
</nav>
```
    
![페이징 된 페이지](https://user-images.githubusercontent.com/74132326/236422673-fc15a1e9-fb45-4d30-b81b-6a1efabe8967.jpg)
    
![페이징 된 페이지2](https://user-images.githubusercontent.com/74132326/236422705-4337bdbf-aea8-4175-bac2-c019c9358219.jpg)

게시판이 내림차순으로 넘어가 2페이지에 글제목 1과 2가 있는것을 알 수 있다 
	
service
```java	
    @Transactional(readOnly = true)
    public Page<Posts> searchKeyword(String keyword,Pageable pageable){
        Page<Posts> page = postsRepository.findByTitleContaining(keyword, pageable); // jpa리포지토리의 containing(=%like) 키워드 사용 
        return page;
    }
```
repository
```java
 Page<Posts> findByTitleContaining(String keyword, Pageable pageable); 
```
controller
```java
    @GetMapping("/posts/search")
    public String searchKeyword(String keyword,Model model,
                                @PageableDefault(sort = "id",direction =Sort.Direction.DESC )Pageable pageable,
                                @LoginUser UserResponseDto user) {
        Page<Posts> page = postsService.searchKeyword(keyword,pageable); //keyword에 따라 검색되는 내용이 바뀌고 검색내용도 페이징
        if(user != null) {
            model.addAttribute("users", user);
        }
        model.addAttribute("search",page);  // 검색된 내용을 페이징하여 
        model.addAttribute("keyword",keyword); //keyword의 정보
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("hasNext",page.hasNext());
        model.addAttribute("hasPrev",page.hasPrevious());
        model.addAttribute("currentPage",pageable.getPageNumber()+1);
        return "posts/postsSearch";
    }

```
	
![페이지 검색](https://user-images.githubusercontent.com/74132326/236425974-5487c543-267f-41fa-b545-de5410c42f02.jpg)

검색창에 5를 검색해 나온 결과 5가붙어있는 제목이 나오는 것을 알 수 있다
	

    
</details>    


**2. 게시글 등록**

<details>
<summary>게시글 등록</summary>


게시글 등록 창 
![게시글 등록 창](https://user-images.githubusercontent.com/74132326/236386043-da25f551-b3b8-4b00-8a2f-5cd5977052a8.jpg)

mustache
```mustache
<div class="col-md-12">
    <div class="container col-md-8">
        <form>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
            </div>
            <input type="text" class="form-control" id="author" value="{{users.nickname}}"> 
            <div class="form-group">
                <label for="content"> 내용 </label>
                <textarea class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
            </div>
        </form>
        <a href="/" role="button" class="btn btn-secondary float-right">취소</a>
        <button type="button" id="btn-save" class="btn btn-primary float-right"  aria-pressed="true">등록</button>
    </div>
</div>
```

service	
```java
    @Transactional
    public Long save(String nickname,PostsSaveDto dto){
        User user=userRepository.findByNickname(nickname);
        dto.setUser(user); //user 정보를 set 

        Posts posts=dto.toEntity(); //받은 정보를 posts에 저장
        postsRepository.save(posts); // persist 

        return posts.getId();
    }
```

controller
```java
    @GetMapping("/posts/save")
    public String postsSave(@LoginUser UserResponseDto user,Model model) {
        if(user != null) {
            model.addAttribute("users", user);
        }
        return "posts/postsSave";
    }
```

api controller
```java
    @PostMapping("/api/v1/posts")
    public ResponseEntity save(@RequestBody PostsSaveDto requestDto, @LoginUser UserResponseDto dto) {
        return ResponseEntity.ok(postsService.save(dto.getNickname(), requestDto)); //ok(200 code) 등록에 성공할 시  
    }
```


등록 버튼을 누르고 정상적일 때 나오는 alert

![등록 버튼을 누른 후](https://user-images.githubusercontent.com/74132326/236386135-9f63b100-abb0-43bf-871e-e4f0babf54fc.jpg)

게시글이 등록 완료되어 테이블에 추가
![게시글 등록 완료](https://user-images.githubusercontent.com/74132326/236386440-42494daf-02f6-4ab1-86cb-af678cc29fe9.jpg)

링크를 타고 들어가면 나오는 게시글 view 

![게시글 view](https://user-images.githubusercontent.com/74132326/236386593-fb2c7635-c558-4b64-9b1d-20df507672c8.jpg)

mustache
```mustache
            <div class="form-group">
                <label for="id">글 번호 : {{post.id}}</label>
                <input type="text" class="form-control" id="id" value="{{post.id}}" readonly>
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" value="{{post.title}}" readonly>
            </div>
            <div class="form-group">
                <label for="author"> 작성자 : {{post.author}} </label>
                <input type="text" class="form-control" id="author" value="{{post.author}}" readonly>
            </div>
            <div class="form-group">
                <label for="content"> 내용 </label>
                <textarea class="form-control" id="content" readonly>{{post.content}}</textarea>
            </div>
	{{#users}} 
            <a href="/" role="button" class="btn btn-secondary">글 목록</a>
            {{#author}}
                <a href="/posts/update/{{post.id}}" role="button" class="btn btn-primary">수정</a> 
                <button type="button" onclick="" class="btn btn-danger" id="btn-delete">삭제</button>
            {{/author}}
        {{/users}}
```

js
```js
        $('#btn-save').on('click', function () {
            _this.save();
        });
	save : function () {
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
```
 </details>


**3. 게시글 수정**

<details>
<summary>게시글 수정</summary>

게시글 수정
	
entity
```java
    public void update(String title,String content){
        this.title=title;
        this.content=content;         //변경감지(dirty checking)
    }
```

service
```java
    @Transactional
    public Long update(Long id,PostsUpdateDto dto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        posts.update(dto.getTitle(),dto.getContent()); 
        return id;
    }
```

controller
```java
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model,
                              @LoginUser UserResponseDto user) {
        PostsResponseDto dto=postsService.findById(id);
        if(user!=null){
            model.addAttribute("users",user);
        }
        model.addAttribute("post",dto);

        return "posts/postsUpdate";
    }
```

api controller
```java
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateDto requestDto) {
        return postsService.update(id,requestDto);
    }
```

js
```js
    $('#btn-update').on('click', function () {
        _this.update();
    });
	'
	'
	'
	update : function () {

        const data = {
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val()
        };


        const check=confirm("글을 수정합니다.");
        if(check===true){
           if (!data.title || data.title.trim() === "" || !data.content || data.content.trim() === "") {
                      alert("입력되지 않았습니다.");
                      return false;
        }
        else{
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+data.id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/view/' + data.id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
        }
      }
    }
```

![게시글 update](https://user-images.githubusercontent.com/74132326/236386735-6ca238f2-f10f-4fce-9b65-91ca5abd11a4.jpg)

게시글 수정 후 

![게시글 update 후](https://user-images.githubusercontent.com/74132326/236386875-b09f6674-085c-4b38-becf-de05481b162d.jpg)
    
</details>

**4. 게시글 삭제**

<details>
<summary>게시글 삭제</summary>

게시글 삭제 
	
service
```java
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        postsRepository.delete(posts);
    }
```

api controller
```java
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
```
	
js
```js
    $('#btn-delete').on('click', function () {
        _this.delete();
    });
    delete : function () {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
```
![게시글 delete](https://user-images.githubusercontent.com/74132326/236387575-d8d23251-0fdf-424f-a568-382468bc2c82.jpg)

게시글 삭제 후 게시판
    
![게시글 delete 완료](https://user-images.githubusercontent.com/74132326/236387624-1258b453-b583-48e3-b70f-a41a8e9fc01a.jpg)

</details>

**5. 회원가입**

<details>
    <summary>회원가입 </summary>

service
```java
    @Transactional
    public void userJoin(UserRequestDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword())); //BCryptoEncoder를 사용해 비밀번호 암호화
        userRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String,String> validate=new HashMap<>(); 

        for (FieldError error : errors.getFieldErrors()) {
            String validKey=String.format("valid_%s",error.getField()); //해쉬 맵에 에러메세지와 에러 정보 저장
            validate.put(validKey,error.getDefaultMessage());

        }
        return validate;
    }
```
repository
```java
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByNickname(String nickname);

    /**
     * 중복 검사 email,username(=user id),nickname(=alias)
     * 중복이면 true , 아니면 false
     */
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
```
	
controller	
```java
    @GetMapping("/auth/join")
    public String join() {
        return "users/userJoin";
    }
	
    @PostMapping("/auth/joinProcedure")
    public String joinProcedure(@Valid UserRequestDto dto, Errors errors, Model model) {
        if(errors.hasErrors()){ //만약 에러가 있을 경우 회원가입 페이지에 에러 정보를 뛰움
            model.addAttribute("userDto",dto);
            Map<String,String> validate=userService.validateHandling(errors); 
            for (String key : validate.keySet()) {
                model.addAttribute(key,validate.get(key)); 
            }
            return "users/userJoin";
        }
        userService.userJoin(dto);
        return "redirect:/auth/login"; // 회원가입이 된 경우 로그인 페이지로 
    }
```

mustache
```mustache
        <form action="/auth/joinProcedure" method="post">
            <input type="hidden" name="_csrf" value="{{_csrf.token}}"/> //mutache는 csrf token을 제공해주지 않아 직접 제공
            <div class="form-group">
                <label>아이디</label>
                <input type="text" class="form-control" name="username"  value="{{#userDto}}{{userDto.username}}{{/userDto}}"  placeholder="아이디를 입력하세요">
                {{#valid_username}} <span id="valid">{{valid_username}}</span> {{/valid_username}} //아이디가 이미 있는 경우 에러를 뛰움(=이미 사용중인 아이디입니다.)
            </div>
            <div class="form-group">
                <label>비밀번호</label>
                <input type="text" class="form-control" name="password"  value="{{#userDto}}{{userDto.password}}{{/userDto}}"  placeholder="비밀번호를 입력하세요">
                {{#valid_password}} <span id="valid">{{valid_password}}</span> {{/valid_password}} // 비밀번호 양식에 맞지 않으면 조건을 뛰움

            </div>
            <div class="form-group">
                <label>닉네임</label>
                <input type="text" class="form-control" name="nickname"  value="{{#userDto}}{{userDto.nickname}}{{/userDto}}"  placeholder="닉네임을 입력하세요">
                {{#valid_nickname}} <span id="valid">{{valid_nickname}}</span> {{/valid_nickname}} // 닉네임이 이미 있는 경우 에러를 뛰움(=이미 사용중인 닉네임 입니다.)

            </div>
            <div class="form-group">
                <label>이메일</label>
                <input type="text" class="form-control" name="email"  value="{{#userDto}}{{userDto.email}}{{/userDto}}"   placeholder="이메일을 입력하세요">
                {{#valid_email}} <span id="valid">{{valid_email}}</span> {{/valid_email}} // 이메일이 이미 있는 경우 에러를 뛰움(=이미 사용중인 이메일 입니다.)
            </div>
            <a href="/" role="button" class="btn btn-info">나가기</a>
            <button class="btn btn-primary" >회원 가입</button>
        </form>
```

회원가입 중복검사 및 유효성 검사

![회원가입 form](https://user-images.githubusercontent.com/74132326/236388055-25cba6e6-2b4c-406f-8a37-ce6598c083d5.jpg)
   
    
</details>
	
	
**6. 로그인**
	
<details>
	<summary>로그인</summary>

login securityconfig 
```java
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**","/posts/view/**","/posts/search/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/loginProcedure")
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
```

mustache
```mustache
        <form action="/auth/loginProcedure" method="post">
            <input type="hidden" name="_csrf" value="{{_csrf.token}}"/>
            <div class="form-group">
                <label>아이디</label>
                <input type="text" class="form-control" name="username" placeholder="아이디를 입력해주세요">
            </div>
            <div class="form-group">
                <label>비밀번호</label>
                <input type="text" class="form-control" name="password" placeholder="비밀번호를 입력해주세요">
            </div>
            <span>
                {{#error}}
                    <p id="valid" class="alert alert-danger">{{exception}}</p>
                {{/error}}
            </span>
            <button class="btn btn-primary">로그인</button>
        </form>	
```

로그인 화면
![login form](https://user-images.githubusercontent.com/74132326/236448628-8b0a1860-4987-4b72-bb59-57a98bd636b5.jpg)
	
로그인 하고 나서
![login after](https://user-images.githubusercontent.com/74132326/236448644-bc6f61a6-fc47-4302-8d90-8849602ad46e.jpg)


</details>
	
**7. 회원 수정**

<details>
	<summary>회원 수정</summary>

service
```java
    public void userUpdate(String nickname, String password){
        this.nickname=nickname;
        this.password=password;  //dirty checking 변경 감지
    }
```
service	
```
    @Transactional
    public void userUpdate(UserRequestDto dto) {
        User user = userRepository.findById(dto.toEntity().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        user.userUpdate(dto.getNickname(), passwordEncoder.encode(dto.getPassword()));

    }
```
	
controller
```java
    @GetMapping("/userUpdate")
    public String userUpdate(@LoginUser UserResponseDto user,Model model){
        if(user!=null){
            model.addAttribute("users",user);

        }
        return "users/userUpdate";
    }
```

api controller
```java
    @PutMapping("/api/v1/users")
    public ResponseEntity<String> userUpdate(@RequestBody UserRequestDto dto) {
        userService.userUpdate(dto);
        Authentication authentication = authenticationManager.authenticate( 
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication); /authentication provider에 전달된 정보를 인증하여 securitycontextholder에 담아서 실행

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
```
	
회원정보 수정
![user update form](https://user-images.githubusercontent.com/74132326/236451335-dcc313ca-8016-4bbe-9958-59eed4b674ca.jpg)
	
hyun2였던 닉네임을 devchyun으로 수정
![update user](https://user-images.githubusercontent.com/74132326/236451625-13145242-e45c-4a10-b20c-70640c2fcf48.jpg)


	
</details>
	
    




    












    

    
