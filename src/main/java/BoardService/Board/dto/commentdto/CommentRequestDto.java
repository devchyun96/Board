package BoardService.Board.dto.commentdto;


import BoardService.Board.domain.Comment;
import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    private Long id;
    private String comment;
    private String createdDate= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String modifiedDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private User user;
    private Posts posts;

    @Builder
    public CommentRequestDto(Long id, String comment, String createdDate, String modifiedDate, User user, Posts posts) {
        this.id = id;
        this.comment = comment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.user = user;
        this.posts = posts;
    }


    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .user(user)
                .posts(posts)
                .build();
    }
}
