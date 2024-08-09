package com.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;

	public Employee saveEmployee(Employee emp) {
		if (emp.getId() == null)
			throw new ValidationException("ID  is mandatory");
		if (emp.getFirstName() == null)
			throw new ValidationException("First name is mandatory");
		
		if (emp.getLastName() == null)
			throw new ValidationException("Last name is mandatory");
		
		if (emp.getEmail() == null) {
			throw new ValidationException("Mail id is mandatory");
		} else if (emp.getEmail() != null) {

			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
			Matcher matcher = pattern.matcher(emp.getEmail());
			if (!matcher.matches()) {
				throw new ValidationException("Give the valid mailId ");
			}
		}
		System.out.println("DOJ"+emp.getDoj());
		
		if (emp.getSalary() == 0) 
			throw new ValidationException("Salary could not be empty");
		
		if(emp.getDoj()==null) 
			throw new ValidationException("Date of joining could not be empty");
		
		if (emp.getPhoneNumbers().contains(",")) {
			String[] list = emp.getPhoneNumbers().split(",");
			for (String s : list) {
				if (s.length() < 10 || s.length() > 10) {
					throw new ValidationException("Moblie number must be 10 numbers only");
				}

				try {
					Long number = Long.parseLong(s);
				} catch (NumberFormatException e) {
					throw new ValidationException("Give valid mobile number");
				}
			}
		} else {

			if (emp.getPhoneNumbers().length() < 10 || emp.getPhoneNumbers().length() > 10) {
				throw new ValidationException("Moblie number must be 10 numbers only");
			}
			try {
				Long number = Long.parseLong(emp.getPhoneNumbers());
			} catch (NumberFormatException e) {
				throw new ValidationException("Give valid mobile number");
			}
		}

		return emp;
		// employeeRepo.save(emp);
	}

	public List<EmployeeTaxPojo> findTaxDetails() {
		List<EmployeeTaxPojo> finalList=new ArrayList<>();
		
		List<Employee> empList=new ArrayList<>();
	
		for(Employee emp:empList){
			EmployeeTaxPojo pojo=new EmployeeTaxPojo();
			pojo.setFirstName(emp.getFirstName());
			pojo.setLastName(emp.getLastName());
			pojo.setYearlySalary(emp.getSalary()*12);
			if(pojo.getYearlySalary()<=250000)
				pojo.setTaxAmount(0);	
			else if(pojo.getYearlySalary()>250000&&pojo.getYearlySalary()<=500000)
			{
				Double tax=pojo.getYearlySalary()*0.05;
				pojo.setTaxAmount(tax);	
			}else if(pojo.getYearlySalary()>500000&&pojo.getYearlySalary()<=1000000){
				Double tax=pojo.getYearlySalary()*0.1;
				pojo.setTaxAmount(tax);	
			}
			else if(pojo.getYearlySalary()>1000000){
				Double tax=pojo.getYearlySalary()*0.2;
				pojo.setTaxAmount(tax);	
			}
			pojo.setCessAmount(0);
			finalList.add(pojo);
		}
		return finalList;
	}

}
