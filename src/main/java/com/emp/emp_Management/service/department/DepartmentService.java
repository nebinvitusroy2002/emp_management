package com.emp.emp_Management.service.department;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Department;
import com.emp.emp_Management.repository.DepartmentRepository;
import com.emp.emp_Management.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentServiceInterface{

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public Department add(Department dept){
        dept.setCreationDate(LocalDate.now());
        try{
            return departmentRepository.save(dept);
        }catch (Exception e){
            throw new RuntimeException("Error creating department");
        }
    }

    public Department update(Long id, Department dept){
        Department d = departmentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Department id not found"));
        d.setName(dept.getName());
        d.setDepartmentHead(dept.getDepartmentHead());
        try {
            return departmentRepository.save(d);
        }catch (Exception e){
            throw new RuntimeException("Error updating department");
        }
    }

    public void delete(Long id){
        try {
            departmentRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting department");
        }
    }

    public PagedResponse<Department> getAll(int page){
        Pageable pageable = PageRequest.of(page,20);
        Page<Department> result = departmentRepository.findAll(pageable);
        try {
            return new PagedResponse<>(result.getContent(), result.getNumber(), result.getTotalPages());
        }catch (Exception e){
            throw new RuntimeException("Error getting the details");
        }
    }

    public Department getById(Long id, boolean expandEmployees){
        try {
            return departmentRepository.findById(id)
                    .orElseThrow(()->new EntityNotFoundException("Error the id does not exists"));
        }catch (Exception e){
            throw new RuntimeException("Error getting the details");
        }
    }
}
