package BoardService.Board.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"),
    GUEST("GUEST"),
    USER("USER");

    private final String key;

}
