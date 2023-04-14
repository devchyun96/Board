package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;

    private int view;
    private int recommend;

    private String createdDate;

    private String modifiedDate;



    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.view=entity.getView();
        this.recommend=entity.getRecommend();
        this.author = entity.getAuthor();
        this.createdDate=entity.getCreatedDate();
        this.modifiedDate=entity.getModifiedDate();
    }
}
