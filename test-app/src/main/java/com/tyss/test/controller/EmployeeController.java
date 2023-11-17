package com.tyss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.test.dto.EmployeeDTO;
import com.tyss.test.dto.Response;
import com.tyss.test.entity.Employee;
import com.tyss.test.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Response> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		String saveEmployee = employeeService.saveEmployee(employeeDTO);
		return new ResponseEntity<Response>(
				Response.builder().data(saveEmployee).message(saveEmployee).error(Boolean.FALSE).build(),
				HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Response> getAllEmployees() {
		List<EmployeeDTO> saveEmployee = employeeService.getAllEmployees();
		return new ResponseEntity<Response>(
				Response.builder().data(saveEmployee).message("Fetched Successfully").error(Boolean.FALSE).build(),
				HttpStatus.OK);
	}

	@GetMapping("/{employeeInfoId}")
	public ResponseEntity<Response> getById(@PathVariable Long employeeInfoId) {
		Employee byId = employeeService.getById(employeeInfoId);
		if (byId == null) {
			return new ResponseEntity<Response>(
					Response.builder().data(byId).message("Employee Not Found").error(Boolean.FALSE).build(),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Response>(
				Response.builder().data(byId).message("Fetched Successfully").error(Boolean.FALSE).build(),
				HttpStatus.OK);
	}

	@DeleteMapping("/{employeeInfoId}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable Long employeeInfoId) {
		String deleteEmployee = employeeService.deleteEmployee(employeeInfoId);
		return new ResponseEntity<Response>(
				Response.builder().data(deleteEmployee).message(deleteEmployee).error(Boolean.FALSE).build(),
				HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		String updateEmployee = employeeService.updateEmployee(employeeDTO);
		return new ResponseEntity<Response>(
				Response.builder().data(updateEmployee).message(updateEmployee).error(Boolean.FALSE).build(),
				HttpStatus.CREATED);
	}

}
