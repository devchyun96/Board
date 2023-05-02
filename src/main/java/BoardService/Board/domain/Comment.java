package BoardService.Board.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "created_date")
    private String createdDate;

    @LastModifiedDate
    @Column(name = "modified_Date")
    private String modifiedDate;

    @Builder
    public Comment(Long id, String comment, Posts posts, User user, String createdDate, String modifiedDate) {
        this.id = id;
        this.comment = comment;
        this.posts = posts;
        this.user = user;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }



    public void commentUpdate(String comment){
        this.comment=comment;
    }

}
