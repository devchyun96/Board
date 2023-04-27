package BoardService.Board.dto.userdto;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;

import lombok.Getter;

import java.io.Serializable;


@Getter
public class UserResponseDto implements Serializable {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Role role;
    private String modifiedDate;

    public UserResponseDto(User userEntity) {
        this.id=userEntity.getId();
        this.username=userEntity.getUsername();
        this.nickname=userEntity.getNickname();
        this.email=userEntity.getEmail();
        this.role=userEntity.getRole();
        this.modifiedDate=userEntity.getModifiedDate();
    }
}
