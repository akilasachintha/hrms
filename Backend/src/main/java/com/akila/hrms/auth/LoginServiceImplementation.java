package com.akila.hrms.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImplementation implements LoginService{

    private final LoginRepository loginRepository;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        UserLogin userLogin = loginRepository.findByEmail(loginRequest.getEmail());

        if(userLogin != null && loginRequest.getPassword().equals(userLogin.getPassword())){
            return new LoginResponse("Success");
        }else {
            return new LoginResponse("Error");
        }
    }
}
