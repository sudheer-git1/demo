package com.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
@Autowired	
private EmployeeService employeeService;	
	
@PostMapping("/save")
public ResponseEntity<?> saveEmployee(@RequestBody Employee emp){
	
		Employee e=employeeService.saveEmployee(emp);
		return new  ResponseEntity<Employee>(emp,HttpStatus.OK);
}
@GetMapping("/taxDetails")
public ResponseEntity<?> getEmployees(){
	try {
		List<EmployeeTaxPojo> list=employeeService.findTaxDetails();
		return new  ResponseEntity<List<EmployeeTaxPojo>>(list,HttpStatus.OK);
	} catch (Exception e) {
		
		return new  ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}  
}
