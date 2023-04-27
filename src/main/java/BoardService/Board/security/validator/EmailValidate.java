package BoardService.Board.security.validator;

import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class EmailValidate extends AbstractValidator<UserRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void Validate(UserRequestDto dto, Errors errors) {
        if(userRepository.existsByEmail(dto.toEntity().getEmail())){
            errors.rejectValue("email","이메일 중복 오류"," 이미 사용중인 이메일 입니다.");
        }
    }
}
