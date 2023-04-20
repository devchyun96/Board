package BoardService.Board.config;

import BoardService.Board.config.auth.CustomOAuth2UserService;
import BoardService.Board.domain.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableJpaAuditing

public class JpaConfig {
}
