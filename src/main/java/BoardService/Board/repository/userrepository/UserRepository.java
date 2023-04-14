package BoardService.Board.repository.userrepository;

import BoardService.Board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

        //Optional<User> findByNickname(String nickname);
}
