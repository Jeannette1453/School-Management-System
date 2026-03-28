package com.springboot.example.school.Student;

import com.springboot.example.school.Response.AddResponse;
import com.springboot.example.school.Response.UpdateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddResponse> addStudent(@Valid @RequestBody StudentDto dto) {
        return new ResponseEntity<>(service.addStudent(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(service.getStudent(id), HttpStatus.OK);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Student>> getStudentsByClassroom(@PathVariable Long classroomId) {
        return new ResponseEntity<>(service.getStudentsByClassroom(classroomId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto dto) {
        return new ResponseEntity<>(service.updateStudent(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
