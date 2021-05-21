package com.studenttest.student.controllers;

import com.studenttest.student.models.Student;
import com.studenttest.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "allStudents")
    public List<Student> getAllStudents() {
        return this.studentService.getStudent();
    }

    @PostMapping(path = "addStudent")
    public void addStudent(@RequestBody Student student) {
        this.studentService.addStudent(student);
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "updateStudent/{studentId}")
    public void upDateStudent
            (@PathVariable("studentId") Long id,
             @RequestParam(required = false) String name,
             @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }
}
