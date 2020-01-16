package app.prit.api.infrastructure.core.repository;

import app.prit.api.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
