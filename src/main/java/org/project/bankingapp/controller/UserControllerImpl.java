package org.project.bankingapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.bankingapp.jwt.JwtTokenProvider;
import org.project.bankingapp.model.UserRequest;
import org.project.bankingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    public ResponseEntity<?> signUp(UserRequest signUpRequest) {
        try {
            userService.signUp(signUpRequest);
            return ResponseEntity.ok("User registered successfully");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getCause());
        }

    }

    public ResponseEntity<?> signIn(UserRequest signInRequest) {
        try {
            if (userService.signIn(signInRequest)) {
                String token = jwtTokenProvider.generateToken(
                        signInRequest
                );
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.badRequest().body("Invalid username/password supplied");
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username/password supplied");
        }
    }
}
