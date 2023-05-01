package BoardService.Board.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //user table PK

    @Column(length = 30,nullable = false,unique = true)
    private String username; //userId

    @Column(length = 100)
    private String password; //password

    @Column(unique = true,nullable = false)
    private String nickname; // alias

    @Column(nullable = false)
    private String email; //email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(Long id,String username, String password, String nickname, String email,Role role) {
        this.id= id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role=role;
    }
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public void userUpdate(String nickname, String password){
        this.nickname=nickname;
        this.password=password;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
