package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveDto {

    private String title;

    private String content;

    private String author;

    private int view;

    private int recommend;

    @Builder
    public PostsSaveDto(String title, String content, String author, int view, int recommend) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.view = view;
        this.recommend = recommend;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .view(0)
                .recommend(0)
                .build();
    }
}
