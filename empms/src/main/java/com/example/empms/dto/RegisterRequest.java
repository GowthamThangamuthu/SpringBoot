package com.example.empms.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @NotNull(message = "role is required")
    private String role; // expects ADMIN, MANAGER, or EMPLOYEE

    // Only required for EMPLOYEE role
    private Long employeeId;
}
