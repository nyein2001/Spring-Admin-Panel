package com.example.springproject.controller;

import com.example.springproject.dao.CustomerDao;
import com.example.springproject.ds.Customer;
import com.example.springproject.security.annotation.customer.CustomerCreate;
import com.example.springproject.security.annotation.customer.CustomerDelete;
import com.example.springproject.security.annotation.customer.CustomerPageView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @CustomerPageView
    @GetMapping("/customers")
    public ModelAndView findAllCustomer() {
        System.out.println(customerDao.findAll());
        return new ModelAndView("customers", "customers", customerDao.findAll());
    }

    @CustomerCreate
    @GetMapping("/customer-form")
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @CustomerCreate
    @PostMapping("/customer-form")
    public String saveCustomer(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer-form";
        }
        customerDao.save(customer);
        return "redirect:/customers/customers";
    }

    @CustomerDelete
    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id") int id) {
        if (customerDao.existsById(id)) {
            customerDao.deleteById(id);
            return "redirect:/customers/customers";
        } else throw new EntityNotFoundException(id + "Not Found");
    }
}
