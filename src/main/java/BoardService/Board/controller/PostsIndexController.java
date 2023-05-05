package BoardService.Board.controller;


import BoardService.Board.domain.Posts;
import BoardService.Board.domain.User;
import BoardService.Board.dto.commentdto.CommentResponseDto;
import BoardService.Board.dto.postsdto.PostsResponseDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.security.auth.LoginUser;
import BoardService.Board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostsIndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size=10,sort="id",direction = Sort.Direction.DESC)Pageable pageable,
                        @LoginUser UserResponseDto user) {
        Page<Posts> posts=postsService.page(pageable);
        if(user != null) {
	        model.addAttribute("users", user);
        }
        model.addAttribute("posts", posts);
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", posts.hasNext());
        model.addAttribute("hasPrev", posts.hasPrevious());
        model.addAttribute("currentPage", pageable.getPageNumber()+1);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(@LoginUser UserResponseDto user,Model model) {
        if(user != null) {
            model.addAttribute("users", user);
        }
        return "posts/postsSave";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model,
                              @LoginUser UserResponseDto user) {
        PostsResponseDto dto=postsService.findById(id);
        if(user!=null){
            model.addAttribute("users",user);
        }
        model.addAttribute("post",dto);

        return "posts/postsUpdate";
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(@PathVariable Long id,Model model,@LoginUser UserResponseDto user,
                            HttpServletRequest request, HttpServletResponse response) {
        PostsResponseDto dto=postsService.findById(id);
        List<CommentResponseDto> comments=dto.getComments();

        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments",comments);
        }

        if(user != null) {
            model.addAttribute("users", user);

            if(dto.getUserId().equals(user.getId())){
                model.addAttribute("author",true);
            }
            if (comments.stream().anyMatch(s -> s.getUserId().equals(user.getId()))) {
                model.addAttribute("isAuthor",true);
            }
        }
        postsService.updateView(id);
        model.addAttribute("post",dto);
        return "posts/postsView";
    }

    @GetMapping("/posts/search")
    public String searchKeyword(String keyword,Model model,
                                @PageableDefault(sort = "id",direction =Sort.Direction.DESC )Pageable pageable,
                                @LoginUser UserResponseDto user) {
        Page<Posts> page = postsService.searchKeyword(keyword,pageable);
        if(user != null) {
            model.addAttribute("users", user);
        }
        model.addAttribute("search",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("hasNext",page.hasNext());
        model.addAttribute("hasPrev",page.hasPrevious());
        model.addAttribute("currentPage",pageable.getPageNumber()+1);
        return "posts/postsSearch";
    }
}
