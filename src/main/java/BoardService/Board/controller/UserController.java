package BoardService.Board.controller;

import BoardService.Board.config.auth.LoginUser;
import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/join")
    public String join(){
        return "/users/userJoin";
    }

    @PostMapping("/users/joinProc")
    public String joinProc(@Valid UserRequestDto dto, Errors errors,Model model){
        if(errors.hasErrors()){
            model.addAttribute("usersDto",dto);
            Map<String,String> validate=userService.validateHandling(errors);
            for (String key : validate.keySet()) {
                model.addAttribute(key,validate.get(key));
            }
            return "/users/userJoin";
        }
        userService.join(dto);
        return "redirect:/users/login";
    }

    @GetMapping("/users/login")
    public String login(@RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "exception",required = false)String exception,
                        Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "/users/userLogin";

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";
    }
    @GetMapping("/users/update")
    public String userUpdate(@LoginUser UserResponseDto user, Model model) {
        if(user!=null){
            model.addAttribute("user",user);
        }
        return "/users/userUpdate";
    }
}
