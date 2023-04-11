package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;
import BoardService.Board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long> ,PostsRepositoryCustom{

    List<Posts> findAllDesc();

    List<Posts> findByUsername(String username);
}
