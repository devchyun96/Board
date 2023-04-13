package BoardService.Board.dto.postsdto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@NoArgsConstructor
public class PostsUpdateDto {
    private String title;
    private String content;

    private Integer view;

    private Integer recommend;

    @Builder
    public PostsUpdateDto(String title, String content, Integer view, Integer recommend) {
        this.title = title;
        this.content = content;
        this.view=view;
        this.recommend=recommend;
    }
}
