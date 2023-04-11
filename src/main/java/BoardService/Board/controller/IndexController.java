package BoardService.Board.controller;

import BoardService.Board.dto.postsdto.PostsResponseDto;
import BoardService.Board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts/postsSave";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts/postsUpdate";
    }

    @GetMapping("/posts/view/{id}")
    public String view(@PathVariable Long id,Model model){
        PostsResponseDto dto=postsService.findById(id);
        postsService.updateView(id);
        return "posts/postsView";
    }
}
