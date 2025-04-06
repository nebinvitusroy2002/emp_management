package com.emp.emp_Management.repository;

import com.emp.emp_Management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT e.id,e.name FROM Employee e")
    List<Object[]> findAllIdAndName();
}
