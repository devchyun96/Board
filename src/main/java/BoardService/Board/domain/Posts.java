package BoardService.Board.domain;

import BoardService.Board.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    private String author;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(columnDefinition = "Integer Default 0",nullable = false)
    private int view;


    @Column(columnDefinition = "Integer Default 0",nullable = false)
    private int recommend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Builder
    public Posts( String title, String author, String content, int view, int recommend, User user) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.view = view;
        this.recommend = recommend;
        this.user=user;
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

}
