package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;
import BoardService.Board.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface PostsRepository extends JpaRepository<Posts,Long> ,PostsRepositoryCustom{

}
