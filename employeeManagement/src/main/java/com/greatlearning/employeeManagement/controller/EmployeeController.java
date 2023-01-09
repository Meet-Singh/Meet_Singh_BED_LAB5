package com.greatlearning.employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.employeeManagement.model.Employee;
import com.greatlearning.employeeManagement.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		List<Employee> employeesResponse = service.getAllEmployees();
		model.addAttribute("employees", employeesResponse);
		return "employees_home_page";
	}

	@GetMapping("/employees/new")
	public String addNewEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
	}

	@GetMapping("/employees/edit/{id}")
	public String updateEmployee(Model model, @PathVariable("id") int id) {
		Employee employee = service.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "edit_employee";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id) {
		service.deleteEmployeeById(id);
		return "redirect:/employees";
	}

	@PostMapping("/employee")
	public String saveNewEmployee(@ModelAttribute("employee") Employee emp) {
		service.saveOrUpdate(emp);
		return "redirect:/employees";
	}

	@PostMapping("/employee/{id}")
	public String UpdateEmployee(@ModelAttribute("employee") Employee emp, @PathVariable("id") int id) {
		Employee existingEmployee = service.getEmployeeById(id);
		existingEmployee.setFirstName(emp.getFirstName());
		existingEmployee.setLastName(emp.getLastName());
		existingEmployee.setEmail(emp.getEmail());
		service.saveOrUpdate(existingEmployee);
		return "redirect:/employees";
	}
}
