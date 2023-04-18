package BoardService.Board.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //user table PK

    @Column(length = 30,nullable = false,unique = true)
    private String username; //userId

    @Column(length = 40,nullable = false)
    private String password; //password

    @Column(length = 30,nullable = false,unique = true)
    private String nickname; // alias

    @Column(nullable = false,unique = true)
    private String email; //email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String username, String password, String nickname, String email,Role role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role=role;
    }

    public User userUpdate(String password, String nickname){
        this.password=password;
        this.nickname=nickname;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
