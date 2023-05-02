package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import BoardService.Board.dto.commentdto.CommentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private int view;
    private int recommend;
    private Long userId;
    private List<CommentResponseDto> comments;
    public PostsResponseDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.author=entity.getAuthor();
        this.view=entity.getView();
        this.recommend=entity.getRecommend();
        this.userId=entity.getUser().getId();
        this.comments=entity.getComments()
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
