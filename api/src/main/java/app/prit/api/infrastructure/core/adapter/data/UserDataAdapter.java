package app.prit.api.infrastructure.core.adapter.data;

import app.prit.api.domain.entity.User;
import app.prit.api.infrastructure.core.port.data.UserDataPort;
import app.prit.api.infrastructure.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataAdapter implements UserDataPort {

    private UserRepository repository;

    @Autowired
    public UserDataAdapter(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
