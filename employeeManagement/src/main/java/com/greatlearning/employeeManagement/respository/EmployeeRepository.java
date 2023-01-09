package com.greatlearning.employeeManagement.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeeManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
