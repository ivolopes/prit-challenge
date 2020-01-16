package app.prit.api.infrastructure.core.port.application;

import app.prit.api.infrastructure.core.rest.user.v1.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserApplicationPort extends UserDetailsService {

    UserDto save(String email, String password);

    UserDto findByEmail(String email);

    User loadUserByEmail(String email);

}
