package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsListDto {

    private Long id;
    private String title;
    private String author;

    private int view;

    private int recommend;
    private String createdDate;

    public PostsListDto(Posts entity) {
        this.id =entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.view=getView();
        this.recommend=getRecommend();
        this.createdDate=getCreatedDate();
    }
}
