package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> ,PostsRepositoryCustom {
    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

}
