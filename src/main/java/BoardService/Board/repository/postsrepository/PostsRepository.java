package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> ,PostsRepositoryCustom {


}
