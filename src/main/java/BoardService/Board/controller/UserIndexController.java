package BoardService.Board.controller;

import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.security.auth.LoginUser;
import BoardService.Board.security.validator.EmailValidate;
import BoardService.Board.security.validator.NicknameValidate;
import BoardService.Board.security.validator.UsernameValidate;
import BoardService.Board.service.UserService;
import lombok.Getter;import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserIndexController {
    private final UserService userService;
    private final UsernameValidate usernameValidate;
    private final NicknameValidate nicknameValidate;
    private final EmailValidate emailValidate;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(usernameValidate);
        binder.addValidators(nicknameValidate);
        binder.addValidators(emailValidate);
    }

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
        userService.userJoin(dto);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "exception",required = false)String exception,
                        Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
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

    @GetMapping("/userUpdate")
    public String userUpdate(@LoginUser UserResponseDto user,Model model){
        if(user!=null){
            model.addAttribute("users",user);

        }
        return "users/userUpdate";
    }

}
