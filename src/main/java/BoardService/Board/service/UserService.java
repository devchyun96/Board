package BoardService.Board.service;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;
import BoardService.Board.dto.userdto.UserRequestDto;
import BoardService.Board.repository.userrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserRequestDto dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setRole(Role.USER);
        userRepository.save(dto.toEntity());
    }

    @Transactional
    public void userUpdate(UserRequestDto dto){
        User user = userRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword= passwordEncoder.encode(dto.getPassword());
        user.userUpdate(dto.getNickname(),encPassword);
    }
}
