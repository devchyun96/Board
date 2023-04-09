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
        List<Posts> list = queryFactory
                .selectFrom(posts)
                .orderBy(posts.id.desc())
                .fetch();
        return list;
    }
}
