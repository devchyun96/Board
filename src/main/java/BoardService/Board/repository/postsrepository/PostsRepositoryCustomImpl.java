package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.Posts;
import BoardService.Board.domain.QPosts;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Predicate;

import static BoardService.Board.domain.QPosts.*;




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
