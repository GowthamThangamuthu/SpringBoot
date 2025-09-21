package com.example.empms.service;

import com.example.empms.dto.AuthResponse;
import com.example.empms.dto.LoginRequest;
import com.example.empms.dto.RegisterRequest;

public interface UserService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
