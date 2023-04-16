package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;

import java.util.List;

public interface PostsRepositoryCustom {

    List<Posts> findAllDesc();

    int updateView(Long id);

    int updateRecommend(Long id);
}
