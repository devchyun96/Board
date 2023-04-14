package BoardService.Board.dto.postsdto;


import BoardService.Board.domain.Posts;
import lombok.*;

@Getter
@NoArgsConstructor
public class PostsSaveDto {
    private String title;
    private String content;
    private String author;

    private int view;

    private int recommend;

    private String createdDate;

    @Builder
    public PostsSaveDto(String title, String content, String author,int view,int recommend,String createdDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.view=view;
        this.recommend=recommend;
        this.createdDate=createdDate;
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
