package BoardService.Board.service;

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
    @Transactional(readOnly = true)
    public void checkUsernameDup(UserRequestDto dto){
        boolean existsByUsername = userRepository.existsByUsername(dto.toEntity().getUsername());
        if(existsByUsername){
            throw new IllegalStateException("같은 아이디가 존재합니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDup(UserRequestDto dto){
        boolean existsByNickname = userRepository.existsByNickname(dto.toEntity().getNickname());
        if(existsByNickname){
            throw new IllegalStateException("같은 닉네임이 존재합니다.");
        }
    }
    @Transactional(readOnly = true)
    public void checkEmailDup(UserRequestDto dto){
        boolean existsByEmail = userRepository.existsByEmail(dto.toEntity().getEmail());
        if(existsByEmail){
            throw new IllegalStateException("같은 이메일이 존재합니다.");
        }
    }

}
