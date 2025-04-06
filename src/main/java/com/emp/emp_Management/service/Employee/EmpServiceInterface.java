package com.emp.emp_Management.service.Employee;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmpServiceInterface {
    Employee createEmp(Employee emp, Long deptId);
    Employee updateEmp(Long id, Employee emp, Long managerId);
    void deleteEmp(Long id);
    Employee updateDepartment(Long empId, Long deptId);
    PagedResponse<Employee> getAll(int page);
    List<Map<String, Object>> getNameAndId();
    Employee getById(Long id);
}
