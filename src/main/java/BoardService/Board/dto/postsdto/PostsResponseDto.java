package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private int recommend;
    private int view;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.author = entity.getAuthor();
        this.recommend = entity.getRecommend();
        this.view = entity.getView();
    }
}
