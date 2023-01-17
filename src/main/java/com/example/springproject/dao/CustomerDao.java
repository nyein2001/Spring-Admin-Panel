package com.example.springproject.dao;

import com.example.springproject.ds.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer> {
}
