package com.example.SpringJpaFirst.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringJpaFirst.model.Employee;
import com.example.SpringJpaFirst.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;

@Service

public class EmployeeService {
	@Autowired
	EmployeeRepository empRepObj;

	public Employee addEmployee(Employee emp) {
		return empRepObj.save(emp);
	}
	
	public List<Employee> addAllEmployee(List<Employee> lstemp){
		return empRepObj.saveAll(lstemp);
	}
	
	public Employee getEmpById(Long id){
		Optional<Employee> obj =  empRepObj.findById(id);
		
		if(!obj.isEmpty()) {
			return obj.get();
		}
		
		return null;
		
	}
	
	public String deleteEmployeebyId(Long id){
		Optional<Employee> obj = empRepObj.findById(id);
		if(obj.isEmpty()) {
			return "No such Employee found with that ID";
		}
		empRepObj.delete(obj.get());
		return "Employee Data deleted successfully";
	}
	
	public Employee updateEmployeeById(Long id){
		Optional<Employee> obj = empRepObj.findById(id);
		if(obj.isEmpty()) {
			return null;
		}

		Employee emp = obj.get();
		emp.setFirstName("XYZ");
		empRepObj.save(emp);
		return emp;
	}
	
	public List<Employee> getAllEmployee(){
		return empRepObj.findAll();
	}
	
	
	
	
	
	
}
