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



    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.author = entity.getAuthor();
    }
}
