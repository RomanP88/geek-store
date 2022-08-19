package com.geekbrains.geekstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
public class SignUpRequestDto {


    @Length(min = 3, max = 255, message = "Длина юзернэйма должна состовлять от 3 до 255 символов")
    private String username;

    private String password;

    private String passwordRepeat;

    private String email;

    public SignUpRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
