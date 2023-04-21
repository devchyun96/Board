package BoardService.Board.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
public class CustomFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private final HttpSession httpSession;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String error;
        if(exception instanceof BadCredentialsException){
            error="아이디 혹은 비밀번호가 일치하지 않습니다.";
        }
        else if(exception instanceof InternalAuthenticationServiceException)
        {
            error="시스템 문제로 인한 요청을 처리할 수 없습니다.";
        }
        else if(exception instanceof UsernameNotFoundException){
            error="계정이 존재하지 않습니다. 회원 가입 후 로그인 해주세요";
        }
        else if(exception instanceof AuthenticationCredentialsNotFoundException){
            error="인증이 거부되었습니다.";
        }
        else {
            error="알 수 없는 이유로 로그인에 실패했습니다.";
        }
        error= URLEncoder.encode(error,"UTF-8"); //UTF-8로 처리
        setDefaultFailureUrl("/auth/login?error=true&exception="+error);

        super.onAuthenticationFailure(request, response, exception);
        httpSession.invalidate(); //세션 삭제
    }
}
