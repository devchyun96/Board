package BoardService.Board.repository.userrepository;

import BoardService.Board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByNickname(String nickname);

    /**
     * 중복 검사 email,username(=user id),nickname(=alias)
     * 중복이면 true , 아니면 false
     */
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
