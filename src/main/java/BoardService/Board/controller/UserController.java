package BoardService.Board.controller;

import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/join")
    public String join(){
        return "/users/userJoin";
    }

    @GetMapping("/users/login")
    public String login(){
        return "/users/userLogin";
    }

    @GetMapping("/users/update")
    public String userUpdate(UserResponseDto user, Model model) {
        if(user!=null){
            model.addAttribute("users",user);

        }
        return "/users/userUpdate";
    }
}
