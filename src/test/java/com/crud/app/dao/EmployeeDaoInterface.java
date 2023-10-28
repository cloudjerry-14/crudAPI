package com.crud.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.crud.app.entity.Employee;

public interface EmployeeDaoInterface extends CrudRepository<Employee, Long>{
	

}
