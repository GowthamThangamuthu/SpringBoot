package com.example.empms.service;

import com.example.empms.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartments();
}
