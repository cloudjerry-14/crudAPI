package com.crud.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crud.app.entity.Employee;
import com.crud.app.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeServiceImpl empServ;

	@GetMapping("/")
	public String hello() {
		return "hi";
	}
	@PostMapping("/createEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee e) {
		empServ.saveData(e);
		return ResponseEntity.ok("Data for Employee : "+ e.getFirstName() + e.getLastName() +" saved successfully!");
	}

	@PostMapping("/addEmployees")
	public ResponseEntity<String>  saveEmployeeList(@RequestBody List<Employee> e) {
		empServ.addEmpList(e);
		return ResponseEntity.ok("All employees were added successfully");
	}

	@GetMapping("/allEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return ResponseEntity.ok(empServ.getData());
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(empServ.getEmpById(id));
	}

	@PutMapping("/update")
	public ResponseEntity<String>  updateEmp(@RequestBody Employee e) {
		empServ.updateEmpDetail(e);
		return ResponseEntity.ok("Employee details updated successfully");
	}

	@PutMapping("/updateEmployeeData/{id}")
	public ResponseEntity<String>  updateEmployeeData(@PathVariable("id") Long id, @RequestBody Employee e) {
		empServ.updateEmpById(id, e);
		return ResponseEntity.ok("Employee Data for Id :" +id +" updated successfully");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeId(@PathVariable("id") Long id) {
		empServ.deleteEmpById(id);
		return ResponseEntity.ok("Employee is deleted with Id : "+id);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<String>  deleteAllEmp() {
		empServ.deletAllEmp();
		return ResponseEntity.ok("All Employee deleted Successfully");
	}

}
