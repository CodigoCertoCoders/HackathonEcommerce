package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.dtos.LoginRequestDTO;

public interface LoginInterface {
    public boolean login(String email,String password);

    public String createToken();
}
