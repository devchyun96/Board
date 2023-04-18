package BoardService.Board.dto.userdto;

import lombok.NoArgsConstructor;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequestDto {
    
    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{6,20}",message = "아이디는 특수 문자를 제외한 6~20자리 입니다.")
    private String username;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_]{2,8}",message = "닉네임은 특수 문자를 제외한 2~8자리 입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",message = "비밀 번호는 8~16자 영문 대 소문자,숫자,특수 문자 입니다.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$",message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private Role role;


    @Builder
    public UserRequestDto(String username, String nickname, String password, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public User toEntity(){
        User user= User.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .email(email)
                .role(role.USER)
                .build();
        return user;
    }
}
