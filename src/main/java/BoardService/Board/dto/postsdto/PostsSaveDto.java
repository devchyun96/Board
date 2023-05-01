package BoardService.Board.dto.postsdto;

import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class PostsSaveDto {

    private String title;

    private String content;

    private String author;

    private int view;

    private int recommend;

    private User user;

    @Builder
    public PostsSaveDto(String title, String content, String author, int view, int recommend,User user) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.view = view;
        this.recommend = recommend;
        this.user=user;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .view(0)
                .recommend(0)
                .user(user)
                .build();
    }
}
