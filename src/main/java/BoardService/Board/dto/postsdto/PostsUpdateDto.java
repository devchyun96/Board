package BoardService.Board.dto.postsdto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateDto {
    private String title;
    private String content;

    private int view;

    private int recommend;

    @Builder
    public PostsUpdateDto(String title, String content,int view, int recommend) {
        this.title = title;
        this.content = content;
        this.view=view;
        this.recommend=recommend;
    }
}
