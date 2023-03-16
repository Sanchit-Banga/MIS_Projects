package com.example.SpringJpaFirst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringJpaFirst.model.Employee;
import com.example.SpringJpaFirst.service.EmployeeService;

@RestController
@RequestMapping("/restservice")
public class EmployeeController {
	@Autowired
	EmployeeService empsrv;
	
	@RequestMapping(value="/addemploy", method = RequestMethod.POST)
	public Employee add(@RequestBody Employee emp){
		return empsrv.addEmployee(emp);
	}
	
	@RequestMapping(value="/addallemploy",method = RequestMethod.POST)
	public List<Employee> addAllEmploylist(@RequestBody List<Employee> emp){
		return empsrv.addAllEmployee(emp);
	}
	
	@RequestMapping(value ="/getemploybyid/{id}",method = RequestMethod.GET)
	public Employee getEmploy(@PathVariable("id") Long id){
		return empsrv.getEmpById(id);
	}
	
	@RequestMapping(value="/updateemploybyid/{id}",method = RequestMethod.PUT)
	public Employee updateEmploy(@PathVariable("id") Long id){
		return empsrv.updateEmployeeById(id);
	}
	
	@RequestMapping(value = "/deleteemploybyid/{id}",method = RequestMethod.DELETE)
	public String deleteEmploy(@PathVariable("id") Long id) {
		return empsrv.deleteEmployeebyId(id);
	}
	
	@RequestMapping(value = "/getallemploy",method = RequestMethod.GET)
	public List<Employee> getAllEmploy() {
		return empsrv.getAllEmployee();
	}
	
	
	
	
	
	

}
