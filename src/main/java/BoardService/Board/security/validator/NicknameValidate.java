package BoardService.Board.security.validator;

import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class NicknameValidate extends AbstractValidator<UserRequestDto> {
    private final UserRepository userRepository;

    @Override
    protected void Validate(UserRequestDto dto, Errors errors) {
        if(userRepository.existsByNickname(dto.toEntity().getNickname())){
            errors.rejectValue("nickname","닉네임 중복 오류"," 이미 사용중인 닉네임 입니다.");
        }
    }
}
