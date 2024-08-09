package com.employee;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee implements Serializable{

private Long id;
@NotNull
private String firstName;
@NotNull
private String lastName;
@Email
@NotNull
private String email;
@NotNull
private String phoneNumbers;
@PastOrPresent
@NotNull
private LocalDate doj;
@NotNull
private double salary;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumbers() {
	return phoneNumbers;
}
public void setPhoneNumbers(String phoneNumbers) {
	this.phoneNumbers = phoneNumbers;
}
@DateTimeFormat(pattern="yyyy-MM-dd")
public LocalDate getDoj() {
	return doj;
}
public void setDoj(LocalDate doj) {
	this.doj = doj;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
@Override
public int hashCode() {
	return Objects.hash(id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Employee other = (Employee) obj;
	return Objects.equals(id, other.id);
}
@Override
public String toString() {
	return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
			+ ", phoneNumbers=" + phoneNumbers + ", doj=" + doj + ", salary=" + salary + "]";
}
public Employee(@NotNull String firstName, @NotNull double salary) {
	super();
	this.firstName = firstName;
	this.salary = salary;
}



}
