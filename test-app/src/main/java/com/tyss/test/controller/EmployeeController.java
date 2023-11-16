package com.tyss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.test.dto.EmployeeDTO;
import com.tyss.test.dto.Response;
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

}
