package BoardService.Board.controller;

import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.service.UserService;
import lombok.Getter;import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserIndexController {
    private final UserService userService;

    @GetMapping("/auth/join")
    public String join() {
        return "users/userJoin";
    }

    @PostMapping("/auth/joinProcedure")
    public String joinProcedure(@Valid UserRequestDto dto, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("userDto",dto);
            Map<String,String> validate=userService.validateHandling(errors);
            for (String key : validate.keySet()) {
                model.addAttribute(key,validate.get(key));
            }
            return "users/userJoin";
        }
        userService.checkUsernameDup(dto);
        userService.checkNicknameDup(dto);
        userService.checkEmailDup(dto);
        userService.userJoin(dto);
        return "redirect:/users/login";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "users/userLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";
    }

}
