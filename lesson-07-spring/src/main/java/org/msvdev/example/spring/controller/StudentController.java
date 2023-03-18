package org.msvdev.example.spring.controller;

import org.msvdev.example.spring.entity.Student;
import org.msvdev.example.spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("newStudent", new Student());
        model.addAttribute("updateStudent", new Student());
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    @PostMapping
    public String addStudent(Student product) {
        studentRepository.save(product);
        return "redirect:/";
    }

    @PutMapping
    public String updateStudent(Student product) {
        studentRepository.save(product);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}