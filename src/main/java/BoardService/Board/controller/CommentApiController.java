package BoardService.Board.controller;


import BoardService.Board.dto.commentdto.CommentRequestDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.security.auth.LoginUser;
import BoardService.Board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("api/v1/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id
            , @RequestBody CommentRequestDto dto
            , @LoginUser UserResponseDto user) {
        return ResponseEntity.ok(commentService.commentSave(user.getNickname(),id ,dto));
    }

    @PutMapping("api/v1/posts/{id}/comments/{commentId}")
    public ResponseEntity commentUpdate(@PathVariable Long id
            , @RequestBody CommentRequestDto dto) {
        commentService.commentUpdate(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("api/v1/posts/{id}/comments/{commentId}")
    public ResponseEntity commentDelete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(id);
    }
}
