package BoardService.Board.repository.postsrepository;

import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    @BeforeEach
    public void clean_first() {
        postsRepository.deleteAll();
    }

    @AfterEach
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_등록() throws Exception{
        //given
        String title= "title";
        String content= "content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("devchyun96")
                .build());


        //when

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        //then
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

}