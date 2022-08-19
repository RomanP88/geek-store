package com.geekbrains.geekstore.converters;

import com.geekbrains.geekstore.dto.SignUpRequestDto;
import com.geekbrains.geekstore.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserConverter {


        public User dtoToEntity(SignUpRequestDto signUpRequestDto) {
            return new User(signUpRequestDto.getUsername(), signUpRequestDto.getPassword(), signUpRequestDto.getEmail());
        }

        public SignUpRequestDto entityToDto(User user) {
            return new SignUpRequestDto(user.getUsername(), user.getPassword(), user.getEmail());
        }

}
