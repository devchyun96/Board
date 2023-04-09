package BoardService.Board.domain.posts;


import BoardService.Board.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private int recommend;

    @Column(nullable = false)
    private int view;



    @Builder
    public Posts(String title, String content, String author, int recommend, int view) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.recommend = recommend;
        this.view = view;

    }

    public void update(String title, String content,int view,int recommend)
    {
        this.title=title;
        this.content=content;
        this.view=view;
        this.recommend=recommend;
    }
}
