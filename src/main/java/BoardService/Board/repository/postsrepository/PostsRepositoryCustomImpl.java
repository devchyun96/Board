package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.posts.Posts;
import BoardService.Board.domain.posts.QPosts;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static BoardService.Board.domain.posts.QPosts.*;

@RequiredArgsConstructor
public class PostsRepositoryCustomImpl implements PostsRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<Posts> findAllDesc() {
        return queryFactory
                .selectFrom(posts)
                .orderBy(posts.id.desc())
                .fetch();
    }

    @Override
    public int updateView(Long id) {
        long count = queryFactory
                .update(posts)
                .set(posts.view, posts.view.add(1))
                .where(posts.id.eq(id))
                .execute();
        return (int)count;
    }

    @Override
    public int updateRecommend(Long id) {
        long recommend = queryFactory
                .update(posts)
                .set(posts.recommend, posts.recommend.add(1))
                .where(posts.id.eq(id))
                .execute();
        return (int)recommend;
    }
}
