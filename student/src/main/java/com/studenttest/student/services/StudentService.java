package com.studenttest.student.services;

import com.studenttest.student.models.Student;
import com.studenttest.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return this.studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("already exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean find = studentRepository.existsById(id);
        if (!find) {
            throw new IllegalStateException("student not find");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("syudent not found")
        );

        if (name != null && name.length() > 0 && !student.getName().equals(name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> student1 = studentRepository.findStudentByEmail(email);
            if (student1.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
