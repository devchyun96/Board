package BoardService.Board.controller;

import BoardService.Board.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }
}
