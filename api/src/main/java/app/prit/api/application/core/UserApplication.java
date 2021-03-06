package app.prit.api.application.core;

import app.prit.api.domain.entity.User;
import app.prit.api.infrastructure.core.port.application.UserApplicationPort;
import app.prit.api.infrastructure.core.port.data.UserDataPort;
import app.prit.api.infrastructure.core.rest.user.v1.dto.UserDto;
import app.prit.api.infrastructure.exceptions.NotAcceptableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserApplication implements UserApplicationPort {

    private UserDataPort userData;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserApplication(UserDataPort userData, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userData = userData;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto save(String name, String email, String password) {

        if( password.length() < 6 ){
            throw new NotAcceptableException("A senha tem que ter no minimo 6 caracteres");
        }

        User user = userData.findByEmail(email);

        if( user != null){
            throw new NotAcceptableException("E-mail já cadastrado");
        }

        user = User.of(name, email, bCryptPasswordEncoder.encode(password));

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
                .name(user.getName())
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
