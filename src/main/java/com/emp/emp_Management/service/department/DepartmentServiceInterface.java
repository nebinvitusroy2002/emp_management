package com.emp.emp_Management.service.department;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Department;

public interface DepartmentServiceInterface {
    Department add(Department dept);
    Department update(Long id, Department dept);
    void delete(Long id);
    PagedResponse<Department> getAll(int page);
    Department getById(Long id, boolean expandEmployees);
}
