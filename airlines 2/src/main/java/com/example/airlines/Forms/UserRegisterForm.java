package com.example.airlines.Forms;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRegisterForm {

    private String nickname;

    private String email;

    private String password;
}
