package BoardService.Board.controller;

import BoardService.Board.dto.postsdto.PostsResponseDto;
import BoardService.Board.dto.postsdto.PostsSaveDto;
import BoardService.Board.dto.postsdto.PostsUpdateDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.security.auth.LoginUser;
import BoardService.Board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity save(@RequestBody PostsSaveDto requestDto, @LoginUser UserResponseDto dto) {
        return ResponseEntity.ok(postsService.save(dto.getNickname(), requestDto));
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateDto requestDto) {
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
