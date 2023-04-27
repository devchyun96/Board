package BoardService.Board.service;

import BoardService.Board.domain.User;
import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void userJoin(UserRequestDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String,String> validate=new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKey=String.format("valid_%s",error.getField());
            validate.put(validKey,error.getDefaultMessage());

        }
        return validate;
    }
    @Transactional
    public void userUpdate(UserRequestDto dto) {
        User user = userRepository.findById(dto.toEntity().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encode = passwordEncoder.encode(dto.getPassword());
        user.userUpdate(dto.getNickname(), encode);

    }

}
