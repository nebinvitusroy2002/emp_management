package com.emp.emp_Management.controller;

import com.emp.emp_Management.dto.PagedResponse;
import com.emp.emp_Management.model.Department;
import com.emp.emp_Management.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> add(@RequestBody Department dept){
        return ResponseEntity.ok(departmentService.add(dept));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Department>> getAll(@RequestParam(defaultValue = "0")int page){
        return ResponseEntity.ok(departmentService.getAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id,
                                              @RequestParam(defaultValue = "false") boolean expand) {
        return ResponseEntity.ok(departmentService.getById(id, expand));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id,@RequestBody Department dept){
        return ResponseEntity.ok(departmentService.update(id, dept));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        departmentService.delete(id);
    }
}
