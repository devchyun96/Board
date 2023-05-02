package BoardService.Board.service;

import BoardService.Board.domain.Comment;
import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import BoardService.Board.dto.commentdto.CommentRequestDto;
import BoardService.Board.repository.commentrepository.CommentRepository;
import BoardService.Board.repository.postsrepository.PostsRepository;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long commentSave(String nickname, Long id, CommentRequestDto dto) {
        User user=userRepository.findByNickname(nickname);
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다" + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment=dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }
}
