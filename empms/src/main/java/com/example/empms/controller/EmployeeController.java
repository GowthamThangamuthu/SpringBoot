package com.example.empms.controller;

import com.example.empms.dto.EmployeeDTO;
import com.example.empms.model.Role;
import com.example.empms.model.User;
import com.example.empms.repository.UserRepository;
import com.example.empms.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserRepository userRepository;

    @PostMapping
    @Operation(summary = "Create a new employee (ADMIN only)")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @GetMapping
    @Operation(summary = "Get employees - ADMIN/MANAGER see all, EMPLOYEE sees own record")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (currentUser.getRole() == Role.EMPLOYEE && currentUser.getEmployee() != null) {
            EmployeeDTO dto = employeeService.getEmployeeById(currentUser.getEmployee().getId());
            return ResponseEntity.ok(List.of(dto));
        }

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
