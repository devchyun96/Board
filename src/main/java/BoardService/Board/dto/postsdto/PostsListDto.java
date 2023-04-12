package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsListDto {

    private Long id;
    private String title;
    private String author;
    private int modifiedDate;

    public PostsListDto(Posts entity) {
        this.id =entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate=getModifiedDate();
    }
}
