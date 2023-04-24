package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsRepositoryCustom {

    List<Posts> findAllDesc();

    int updateView(Long id);

    int updateRecommend(Long id);




}
