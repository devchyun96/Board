package BoardService.Board.dto.postsdto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateDto {

    private String title;
    private String content;

    public PostsUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
