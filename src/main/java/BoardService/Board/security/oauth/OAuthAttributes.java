package BoardService.Board.security.oauth;

import BoardService.Board.domain.Role;
import BoardService.Board.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String username, String nickname, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .username((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    public User toEntity() {
        return User.builder()
                .username(email)
                .nickname(nickname)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
}
