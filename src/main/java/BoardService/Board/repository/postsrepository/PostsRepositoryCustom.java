package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface PostsRepositoryCustom {

    List<Posts> findAllDesc();

    @Modifying
    long updateView(Long id);
}
