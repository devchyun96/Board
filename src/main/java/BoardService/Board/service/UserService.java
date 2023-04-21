package BoardService.Board.service;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;
import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserRequestDto dto){  //회원 가입
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setRole(Role.USER);
        userRepository.save(dto.toEntity());
    }

    @Transactional //회원 수정
    public void userUpdate(UserRequestDto dto){
        User user = userRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword= passwordEncoder.encode(dto.getPassword());
        user.userUpdate(dto.getNickname(),encPassword);
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) { //유효성,중복 검사
        Map<String, String> validate=new HashMap<>();

        for(FieldError error: errors.getFieldErrors()){
            String validKey=String.format("valid_%s",error.getField());
            validate.put(validKey,error.getDefaultMessage());
        }
        return validate;
    }
}
