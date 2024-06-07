package com.example.examproject.controller;



import com.example.examproject.model.Employee;
import com.example.examproject.model.Post;
import com.example.examproject.repository.EmployeRepo;
import com.example.examproject.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeController {

    @Autowired
    private EmployeRepo employeeRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("posts", Post.values());
        model.addAttribute("projects", projectRepository.findAll());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employe) {
        employeeRepository.save(employe);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}

