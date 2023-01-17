package com.example.springproject.dao;

import com.example.springproject.ds.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDao extends CrudRepository<Department, Integer> {
}
