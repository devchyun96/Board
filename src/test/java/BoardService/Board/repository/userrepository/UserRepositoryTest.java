package BoardService.Board.repository.userrepository;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @AfterEach
    public void clean() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원_찾기() throws Exception{
        //given
        String username="devchyun96";
        String password="asdf1234!";
        String encodePassword=passwordEncoder.encode(password);

        userRepository.save(User.builder()
                        .username(username)
                        .password(encodePassword)
                        .nickname("hyun96")
                        .email("devchyun96@naver.com")
                        .role(Role.USER)
                .build());
        //when

        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        //then
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(encodePassword);
    }

}