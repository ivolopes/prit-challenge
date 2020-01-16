package app.prit.api.application.core;

import app.prit.api.domain.entity.User;
import app.prit.api.infrastructure.core.port.application.UserApplicationPort;
import app.prit.api.infrastructure.core.port.data.UserDataPort;
import app.prit.api.infrastructure.core.rest.user.v1.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserApplication implements UserApplicationPort {

    private UserDataPort userData;

    @Autowired
    public UserApplication(UserDataPort userData){
        this.userData = userData;
    }

    @Override
    public UserDto save(String email, String password) {

        User user = User.builder()
                .email(email)
                .password(password).build();

        user = userData.save(user);

        return this.convertToDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userData.findByEmail(email);

        if( user != null ){
            return this.convertToDto(user);
        }else{
            return  null;
        }
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByEmail(String email) {

        User user = userData.findByEmail(email);

        if( user != null ) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
        } else{
            return null;
        }
    }

    private UserDto convertToDto(User user){
        return UserDto.builder().id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User applicationUser = userData.findByEmail(s);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(s);
        }
        return new org.springframework.security.core.userdetails.User(applicationUser.getEmail(), applicationUser.getPassword(), new ArrayList<>());
    }
}
