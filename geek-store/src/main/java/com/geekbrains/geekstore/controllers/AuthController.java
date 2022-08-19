package com.geekbrains.geekstore.controllers;

import com.geekbrains.geekstore.dto.AuthRequest;
import com.geekbrains.geekstore.dto.AuthResponse;
import com.geekbrains.geekstore.dto.SignUpRequestDto;
import com.geekbrains.geekstore.exceptions.MarketError;
import com.geekbrains.geekstore.exceptions.ResourceNotFoundException;
import com.geekbrains.geekstore.services.AuthService;
import com.geekbrains.geekstore.services.UserService;
import com.geekbrains.geekstore.utils.JwtTokenUtil;
import com.geekbrains.geekstore.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    private final UserValidator userValidator;

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }



    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@RequestBody @Validated SignUpRequestDto signUpRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ResourceNotFoundException("Ошибка валидации");
        }

        userValidator.validatePassword(signUpRequestDto);

        userService.isUsernameOrEmailUsedAllReady(signUpRequestDto.getUsername(), signUpRequestDto.getEmail());

        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }









}

