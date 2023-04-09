package BoardService.Board.dto.postsdto;


import BoardService.Board.domain.posts.Posts;
import lombok.*;

import java.time.LocalDateTime;

@Getter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsSaveDto {


    private String title;
    private String content;
    private String author;
    private int view;
    private int recommend;


    @Builder
    public PostsSaveDto(Long id, String title, String content, String author, int view, int recommend) {
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
                .recommend(recommend)
                .build();
    }
}
