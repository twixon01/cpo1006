package com.example.airlines.Forms;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLoginForm {

    private String email;

    private String password;
}
