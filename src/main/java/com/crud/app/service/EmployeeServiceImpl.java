package com.crud.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crud.app.dao.EmployeeDaoInterface;
import com.crud.app.entity.Employee;


@Service
public class EmployeeServiceImpl {

	@Autowired
	EmployeeDaoInterface empDao;

	public void saveData(Employee e) {
		// TODO Auto-generated method stub
		empDao.save(e);
	}

	public List<Employee> getData() {
		// TODO Auto-generated method stub
		if(empDao.findAll() ==null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, " There are no employees to disply ");
		}
		return (List<Employee>) empDao.findAll();
	}

	public Employee getEmpById(Long id) {
		// TODO Auto-generated method stub
		if (!empDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist with the ID : " + id);
		}
		return empDao.findById(id).get();
	}

	public void updateEmpById(Long id, Employee e) {
		// TODO Auto-generated method stub
		if (!empDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist with the ID : " + id);
		}
		
		Employee emp = empDao.findById(id).get();

		emp.setFirstName(e.getFirstName());
		emp.setLastName(e.getLastName());
		emp.setEmailId(e.getEmailId());

		empDao.save(emp);
		
	}

	public void updateEmpDetail(Employee e) {
		// TODO Auto-generated method stub
			empDao.save(e);
	}

	public void deleteEmpById(Long id) {
		// TODO Auto-generated method stub
		if (!empDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist with the ID : " + id);
		}
		empDao.deleteById(id);
	}

	public void deletAllEmp() {
		// TODO Auto-generated method stub
		empDao.deleteAll();
	}

	public void addEmpList(List<Employee> e) {
		// TODO Auto-generated method stub
		empDao.saveAll(e);
	}

}
