package BoardService.Board.dto.commentdto;

import BoardService.Board.domain.Comment;
import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String createdDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String modifiedDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String nickname;
    private Long postsId;

    public CommentResponseDto(Comment comment) {
        this.id=comment.getId();
        this.comment=comment.getComment();
        this.createdDate=comment.getCreatedDate();
        this.modifiedDate=comment.getModifiedDate();
        this.nickname=comment.getUser().getNickname();
        this.postsId=comment.getPosts().getId();
    }
}
