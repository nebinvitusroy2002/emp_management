package com.emp.emp_Management.controller;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Employee;
import com.emp.emp_Management.service.Employee.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmpController {

    private final EmpService empService;

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody Employee emp,@RequestParam Long deptId,@RequestParam(required = false)Long managerId){
        return ResponseEntity.ok(empService.createEmp(emp, deptId));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Employee>> getAll(@RequestParam(defaultValue = "0")int page){
        return ResponseEntity.ok(empService.getAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        return ResponseEntity.ok(empService.getById(id));
    }

    @GetMapping("/lookup")
    public ResponseEntity<List<Map<String,Object>>> lookup(){
        return ResponseEntity.ok(empService.getNameAndId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee e, @RequestParam(required = false)Long managerId) {
        return ResponseEntity.ok(empService.updateEmp(id, e, managerId));
    }

    @PutMapping("/{id}/department")
    public ResponseEntity<Employee> updateDepartment(@PathVariable Long id,@RequestParam Long deptId){
        return ResponseEntity.ok(empService.updateDepartment(id,deptId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        empService.deleteEmp(id);
    }
}
