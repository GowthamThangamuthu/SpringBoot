package com.example.empms.service.impl;

import com.example.empms.dto.AuthResponse;
import com.example.empms.dto.LoginRequest;
import com.example.empms.dto.RegisterRequest;
import com.example.empms.model.Employee;
import com.example.empms.model.Role;
import com.example.empms.model.User;
import com.example.empms.repository.EmployeeRepository;
import com.example.empms.repository.UserRepository;
import com.example.empms.security.JwtUtil;
import com.example.empms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Role role = Role.valueOf(request.getRole().toUpperCase());
        Employee linkedEmployee = null;

        // If role is EMPLOYEE, link to an existing employee
        if (role == Role.EMPLOYEE && request.getEmployeeId() != null) {
            linkedEmployee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found for linking"));
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .employee(linkedEmployee)
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String jwtToken = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
