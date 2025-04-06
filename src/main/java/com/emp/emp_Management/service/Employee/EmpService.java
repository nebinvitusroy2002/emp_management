package com.emp.emp_Management.service.Employee;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Department;
import com.emp.emp_Management.model.Employee;
import com.emp.emp_Management.repository.DepartmentRepository;
import com.emp.emp_Management.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmpService implements EmpServiceInterface{

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public Employee createEmp(Employee employee,Long departmentId){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(()->new EntityNotFoundException("Department not found"));

        employee.setDepartment(department);
        employee.setName(employee.getName());
        employee.setDob(employee.getDob());
        employee.setAddress(employee.getAddress());
        employee.setSalary(employee.getSalary());
        employee.setRole(employee.getRole());
        employee.setJoiningDate(employee.getJoiningDate());
        employee.setYearlyBonusPercentage(employee.getYearlyBonusPercentage());
        try{
            return employeeRepository.save(employee);
        }catch (Exception e){
            throw new RuntimeException("Error while creating employee");
        }
    }

    public Employee updateEmp(Long id,Employee emp,Long managerId){
        Employee e = employeeRepository.findById(id).orElseThrow();
        Employee manager = null;
        if (managerId != null){
            manager=employeeRepository.findById(managerId)
                    .orElseThrow(()-> new EntityNotFoundException("Manager not found"));
        }
        e.setReportingManager(manager);
        e.setName(emp.getName());
        e.setDob(emp.getDob());
        e.setAddress(emp.getAddress());
        e.setSalary(emp.getSalary());
        e.setRole(emp.getRole());
        e.setJoiningDate(emp.getJoiningDate());
        e.setYearlyBonusPercentage(emp.getYearlyBonusPercentage());
        try{
            return employeeRepository.save(e);
        }catch (Exception ex){
            throw new RuntimeException("Error while updating");
        }
    }

    public void deleteEmp(Long id){
        try {
            employeeRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Employee deletion failed");
        }
    }

    public Employee updateDepartment(Long empId, Long deptId){
        Employee e = employeeRepository.findById(empId)
                .orElseThrow(()->new EntityNotFoundException("Employee id not found"));
        e.setDepartment(departmentRepository.findById(deptId)
                .orElseThrow(()->new EntityNotFoundException("Department id not found")));
        try {
            return employeeRepository.save(e);
        }catch (Exception ex){
            throw new RuntimeException("Employee department update failed");
        }
    }

    public PagedResponse<Employee> getAll(int page){
        Pageable pageable = PageRequest.of(page,20);
        Page<Employee> result = employeeRepository.findAll(pageable);
        return new PagedResponse<>(result.getContent(), result.getNumber(), result.getTotalPages());
    }

    public List<Map<String,Object>> getNameAndId(){
        List<Object[]> list = employeeRepository.findAllIdAndName();
        List<Map<String,Object>> result = new ArrayList<>();
        for (Object[] row:list){
            Map<String,Object> map = new HashMap<>();
            map.put("id",row[0]);
            map.put("name",row[1]);
            result.add(map);
        }
        try {
            return result;
        }catch (Exception e){
            throw new RuntimeException("Error while getting name and id");
        }
    }

    public Employee getById(Long id){
        try {
            return employeeRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("Error getting the employee"));
        }catch (Exception e){
            throw new RuntimeException("Error getting the details");
        }
    }

}
