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
    private String content;
    private int view;
    private int recommend;
    private String createdDate;

    public PostsListDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.author=entity.getAuthor();
        this.content=entity.getContent();
        this.view=entity.getView();
        this.recommend=entity.getRecommend();
        this.createdDate=entity.getCreatedDate();
    }
}
