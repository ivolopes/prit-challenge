package app.prit.api.infrastructure.core.port.data;

import app.prit.api.domain.entity.User;

public interface UserDataPort {

    User save(User user);

    User findByEmail(String email);

}
