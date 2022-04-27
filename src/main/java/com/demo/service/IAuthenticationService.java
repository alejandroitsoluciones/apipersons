package com.demo.service;

import com.demo.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
