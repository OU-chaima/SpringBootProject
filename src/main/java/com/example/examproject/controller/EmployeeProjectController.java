package com.example.examproject.controller;


import com.example.examproject.model.Employee;
import com.example.examproject.model.Project;
import com.example.examproject.repository.EmployeRepo;
import com.example.examproject.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeProjectController {

    @Autowired
    private EmployeRepo employeeRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "assign";
    }

    @PostMapping("/assign")
    public String assignEmployeeToProject(@RequestParam Long employeeId, @RequestParam Long projectId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + employeeId));
       Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + projectId));

        employee.getProjects().add(project);
        employeeRepository.save(employee);

        return "redirect:/assign";
    }
}

