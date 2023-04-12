package BoardService.Board.domain.posts;


import BoardService.Board.domain.BaseTimeEntity;
import BoardService.Board.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name="posts_id")
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Column(columnDefinition = "Integer default 0")
    private int view;

    @Column(columnDefinition = "Integer default 0")
    private int recommend;





    @Builder
    public Posts(String title, String content, String author,int view,int recommend) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.view=view;
        this.recommend=recommend;
    }

    public void update(String title, String content)
    {
        this.title=title;
        this.content=content;

    }
}
