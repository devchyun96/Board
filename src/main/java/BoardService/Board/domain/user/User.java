package BoardService.Board.domain.user;


import BoardService.Board.domain.BaseTimeEntity;
import BoardService.Board.domain.posts.Posts;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false,unique = true,length = 100) //user id
    private String username;

    @Column(nullable = false,unique = true,length = 100)
    private String nickname;

    @Column(length = 100,nullable = false)
    private String password;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void userUpdate(String nickname,String password)
    {
        this.nickname=nickname;
        this.password=password;
    }

}
