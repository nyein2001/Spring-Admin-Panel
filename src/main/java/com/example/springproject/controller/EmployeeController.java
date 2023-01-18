package com.example.springproject.controller;

import com.example.springproject.dao.EmployeeDao;
import com.example.springproject.ds.Employee;
import com.example.springproject.security.annotation.employee.EmployeeCreate;
import com.example.springproject.security.annotation.employee.EmployeeDelete;
import com.example.springproject.security.annotation.employee.EmployeePageView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @EmployeePageView
    @GetMapping("/employees")
    public ModelAndView findAllEmployees(){
        return new ModelAndView("employees", "employees", employeeDao.findAll());
    }

    @EmployeeCreate
    @GetMapping("/employee-form")
    public String employeeForm(Model model) {
        model.addAttribute("employees", new Employee());
        return "employee-form";
    }

    @EmployeeCreate
    @PostMapping("/employee-form")
    public String saveEmployee(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "employee-form";
        }
        employeeDao.save(employee);
        return "redirect:/employee/employees";
    }

    @EmployeeDelete
    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        if (employeeDao.existsById(id)) {
            employeeDao.deleteById(id);
            return "redirect:/employee/employees";
        } else throw new EntityNotFoundException(id + "Not Found");
    }
}
