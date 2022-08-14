package com.geekbrains.geekstore.validators;

import com.geekbrains.geekstore.dto.SignUpRequestDto;
import org.springframework.stereotype.Component;
@Component
public class UserValidator {

    public boolean validatePassword(SignUpRequestDto signUpRequestDto){
        if(signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordRepeat())){
            return true;
        }
        return false;
    }
}
