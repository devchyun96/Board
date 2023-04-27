package BoardService.Board.security.validator;

import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.dto.userdto.UserResponseDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class UsernameValidate extends AbstractValidator<UserRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void Validate(UserRequestDto dto, Errors errors) {
        if(userRepository.existsByUsername(dto.toEntity().getUsername())){
            errors.rejectValue("username","아이디 중복 오류"," 이미 사용중인 아이디 입니다.");
        }
    }
}
