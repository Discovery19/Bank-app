package org.project.bankingapp.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.project.bankingapp.entity.User;
import org.project.bankingapp.model.UserRequest;
import org.project.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signUp(UserRequest signUpRequest) {
        User user = new User();
        user.setLogin(signUpRequest.login());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        userRepository.saveAndFlush(user);
    }

    public boolean signIn(UserRequest signInRequest) {
        var user = userRepository.findByLogin(signInRequest.login());
        return user.filter(value -> passwordEncoder.matches(signInRequest.password(), value.getPassword())).isPresent();
    }
    @PostConstruct
    void createTestUsers() {
        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword(passwordEncoder.encode("123"));
        user1.setBalance(15);
        User user2 = new User();
        user2.setLogin("user2");
        user2.setPassword(passwordEncoder.encode("123"));
        user2.setBalance(25);
        userRepository.saveAndFlush(user1);
        userRepository.saveAndFlush(user2);
    }
}
