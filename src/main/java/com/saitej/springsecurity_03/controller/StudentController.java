package com.saitej.springsecurity_03.controller;



import com.saitej.springsecurity_03.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value ="api/v1/students")
public class StudentController {

    List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"), new Student(2, "SpiderMan"),
            new Student(3, "IronMan"));

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst().
                orElseThrow(() -> new IllegalArgumentException("Student " + studentId + " does not exist"));

    }

}
