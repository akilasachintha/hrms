package com.akila.hrms.auth;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);

    }
}
