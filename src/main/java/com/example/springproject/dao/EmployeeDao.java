package com.example.springproject.dao;

import com.example.springproject.ds.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
}
