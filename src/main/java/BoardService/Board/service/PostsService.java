package BoardService.Board.service;

import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import BoardService.Board.dto.postsdto.PostsListDto;
import BoardService.Board.dto.postsdto.PostsResponseDto;
import BoardService.Board.dto.postsdto.PostsSaveDto;
import BoardService.Board.dto.postsdto.PostsUpdateDto;
import BoardService.Board.repository.postsrepository.PostsRepository;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final UserRepository userRepository;
    @Transactional
    public Long save(String nickname,PostsSaveDto dto){
        User user=userRepository.findByNickname(nickname);
        dto.setUser(user);

        Posts posts=dto.toEntity();
        postsRepository.save(posts);

        return posts.getId();
    }

    @Transactional
    public Long update(Long id,PostsUpdateDto dto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        posts.update(dto.getTitle(),dto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));
        postsRepository.delete(posts);
    }


    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다 id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public int updateView(Long id){
        return postsRepository.updateView(id);
    }

    @Transactional(readOnly = true)
    public Page<Posts> page(Pageable pageable){
        return postsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Posts> searchKeyword(String keyword,Pageable pageable){
        Page<Posts> page = postsRepository.findByTitleContaining(keyword, pageable);
        return page;
    }

}
