package com.example.springproject.controller;

import com.example.springproject.dao.CustomerDao;
import com.example.springproject.dao.DepartmentDao;
import com.example.springproject.ds.Customer;
import com.example.springproject.ds.Department;
import com.example.springproject.security.annotation.customer.CustomerCreate;
import com.example.springproject.security.annotation.customer.CustomerDelete;
import com.example.springproject.security.annotation.customer.CustomerPageView;
import com.example.springproject.security.annotation.department.DepartmentCreate;
import com.example.springproject.security.annotation.department.DepartmentPageView;
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
@RequestMapping(path = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    @DepartmentPageView
    @GetMapping("/departments")
    public ModelAndView findAllDepartment() {
        return new ModelAndView("departments", "departments", departmentDao.findAll());
    }

    @DepartmentCreate
    @GetMapping("/department-form")
    public String departmentForm(Model model) {
        model.addAttribute("departments", new Department());
        return "department-form";
    }

    @DepartmentCreate
    @PostMapping("/department-form")
    public String saveDepartment(@Valid Department department, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "department-form";
        }
        departmentDao.save(department);
        return "redirect:/departments/departments";
    }

    @CustomerDelete
    @GetMapping("/departments/delete")
    public String deleteDepartment(@RequestParam("id") int id) {
        if (departmentDao.existsById(id)) {
            departmentDao.deleteById(id);
            return "redirect:/departments/departments";
        } else throw new EntityNotFoundException(id + "Not Found");
    }
}

